package ddt.framework.model.search

import org.apache.commons.lang3.StringUtils

import java.util.function.Predicate
import java.util.stream.Collectors

/**
 * Model for AEM product. Encapsulates json object, representing product, and
 * a list of skus.
 *
 * @author mikhail_bazhenov
 */
public class Product {
    private final Object json
    private final List<String> skus

    Product(final Object json) {
        this.json = json
        if (json instanceof Map)
            skus = (json as Map).keySet().stream().filter(new Predicate<String>() {
                @Override
                boolean test(String o) {
                    return StringUtils.isNumeric(o)
                }
            }).collect(Collectors.toList())
        else
            skus = []
    }

    Object getJson() {
        return json
    }

    List<String> getSkus() {
        return skus
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Product product = (Product) o

        if (json != product.json) return false

        return true
    }

    int hashCode() {
        return (json != null ? json.hashCode() : 0)
    }
}