package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ButtonSearchRule extends AbstractSearchRule {

    public ButtonSearchRule() {
        super();
    }

    public ButtonSearchRule(String tag, boolean searchText, List<String> classes, List<ElementAttribute> attributes) {
        super(tag, searchText, classes, attributes);
    }

    @Override
    public Elements extractElementsFromWebSite(List<String> urls) {
        return null;
    }

    @Override
    public Elements extractElementsFromWebSite(String url) {
        Elements searchResults = new Elements();
        Document document = getURLConnection(url);

        searchResults.addAll(searchElementsByTag(document));
        searchResults.retainAll(searchElementsByClasses(document));
        searchResults.retainAll(searchElementsByAttributes(document));

        return new Elements(searchResults);
    }

}