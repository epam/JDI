package com.epam.page.object.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ElementsFinder {

    // метод, который ищет элементы по набору правил
    public static Map<SearchRule, Elements> searchElementsByRulesOnURLs(List<SearchRule> rules, List<String> urls) throws Exception {
        Map<SearchRule, Elements> searchResults = new HashMap<>();

        for (String currentURL : urls) {
            for (SearchRule currentRule : rules) {
                searchResults.put(currentRule, searchElementsByRuleOnURL(currentRule, currentURL));
            }
        }

        return searchResults;
    }

    //    метод для поиска элементов по отдельному правилу при помощи JSOUP
    private static Elements searchElementsByRuleOnURL(SearchRule rule, String url) throws Exception {
        Document document = Jsoup.connect(url).get();
        Elements resultsOfSearch = new Elements();
        Elements resultsOfSearchByTag = new Elements();
        Elements resultsOfSearchByClasses = new Elements();
        Elements resultsOfSearchByAttributes = new Elements();

//Для оффлайн-режима
//        String html = "<p>An <a href='http://example.com/'><b class=\"testclass1 testclass2\" attr1=\"attr1value\" attr2=\"attr2value\">example</b></a> link.</p>";
//        Document document = Jsoup.parse(html);

        if (rule.getTag() != null) {
            resultsOfSearchByTag = document.select(rule.getTag());
        }

        if (rule.getClasses() != null) {
            resultsOfSearchByClasses = document.select(prepareCSSQuerySelectorByClasses(rule.getClasses()));
        }

        if (rule.getAttributes() != null) {
            resultsOfSearchByAttributes = searchElelemntsInDocumentByAttributeValues(document, rule.getAttributes());
        }

        resultsOfSearchByTag.retainAll(resultsOfSearchByClasses);
        resultsOfSearchByTag.retainAll(resultsOfSearchByAttributes);

        return resultsOfSearchByTag;
    }


    private static String prepareCSSQuerySelectorByClasses (List<String> classes) {
        StringBuilder selector = new StringBuilder();

        for (String currentClass: classes) {
            selector.append(".");
            selector.append(currentClass);
        }

        return selector.toString();
    }

    private static Elements searchElelemntsInDocumentByAttributeValues (Element document, List<ElementAttribute> attributes) {
        Elements searchResults = new Elements();

        checkNextElement:
        for( Element currentElement : document.getAllElements() )
        {
            for (ElementAttribute currentAttribute: attributes) {
                if (currentElement.attr(currentAttribute.getAttributeName()) == null || !currentElement.attr(currentAttribute.getAttributeName()).equals(currentAttribute.getAttributeValue())) {
                    continue checkNextElement;
                }
            }

            searchResults.add(currentElement);
        }

        return searchResults;
    }
}



