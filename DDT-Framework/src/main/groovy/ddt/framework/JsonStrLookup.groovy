package ddt.framework

import ddt.framework.model.search.Product
import org.apache.commons.lang3.text.StrLookup
import java.util.regex.Pattern

/**
 * Lookup implementation that uses a JSON.
 *
 * @author mikhail_bazhenov
 */
public class JsonStrLookup<V> extends StrLookup<V> {

    private static final Pattern SKU_PATTERN = ~/sku\[(\d+)\]/

    private final Product product
    private final String alias

    /**
     * Creates a new instance backed by a JSON.
     *
     * @param json json object, may be null
     * @param alias json object alias used in expression
     */
    JsonStrLookup(final Product product, final String alias) {
        this.product = product
        this.alias = alias
    }

    @Override
    String lookup(final String key) {
        if (product == null) {
            return null
        }

        if (!key.startsWith(alias)) {
            return null
        }

        final obj = Eval.me(alias, product.getJson(), convertSkuIndexToSkuNumber(key))
        if (obj == null) {
            return null
        }

        return obj.toString()
    }

    private String convertSkuIndexToSkuNumber(final String key) {
        return key.replaceAll(~/.sku\[(\d+)\]/, {
            /["${getSkuNumberByIndex(Integer.valueOf(it[1] as String))}"]/
        })
    }

    private String getSkuNumberByIndex(int idx) {
        return product.getSkus().get(idx)
    }
}

