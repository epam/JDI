package com.epam.page.object.generator.finder;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.SearchRule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElementsFinder {

	/**
	 * Connect by urls and searching suitable elements by input search rules.
	 * @param rules Search rules.
	 * @param urls URLs.
	 * @return Map with all elements for suitable search rule (rule is key for map).
	 * @throws IOException If can't connect by current URL.
	 * @throws IllegalArgumentException If URL format is not valid.
	 */
	public static Map<SearchRule, Elements> searchElementsByRulesOnURLs(List<SearchRule> rules, List<String> urls)
		throws IOException, IllegalArgumentException {
        Map<SearchRule, Elements> searchResults = new HashMap<>();

        for (String currentURL : urls) {
			Document currentDocument = Jsoup.connect(currentURL).get();

            for (SearchRule currentRule : rules) {
                searchResults.put(currentRule, searchElementsByRuleOnURL(currentRule, currentDocument));
            }
        }

        return searchResults;
    }

    private static Elements searchElementsByRuleOnURL(SearchRule rule, Document document) {
        Elements resultsOfSearch = new Elements();
        Elements resultsOfSearchByTag;
		Elements resultsOfSearchByClasses;
		Elements resultsOfSearchByAttributes;

		if (document != null) {
			if (rule.getTag() != null) {
				resultsOfSearchByTag = document.select(rule.getTag());
				resultsOfSearch = resultsOfSearchByTag;
			}

			if (rule.getClasses() != null && !rule.getClasses().isEmpty()) {
				resultsOfSearchByClasses = document.select(prepareCSSQuerySelectorByClasses(rule.getClasses()));

				if (rule.getTag() == null) {
				    resultsOfSearch = resultsOfSearchByClasses;
                } else {
                    resultsOfSearch.retainAll(resultsOfSearchByClasses);
                }
			}

			if (rule.getAttributes() != null && !rule.getAttributes().isEmpty()) {
				resultsOfSearchByAttributes = searchElementsInDocumentByAttributeValues(document, rule.getAttributes());

                if (rule.getTag() == null && (rule.getClasses().isEmpty() || rule.getClasses() == null)) {
                    resultsOfSearch = resultsOfSearchByAttributes;
                } else {
                    resultsOfSearch.retainAll(resultsOfSearchByAttributes);
                }
			}
		}

        return resultsOfSearch;
    }

    private static String prepareCSSQuerySelectorByClasses (List<String> classes) {
        StringBuilder selector = new StringBuilder();

        for (String currentClass: classes) {
            selector.append(".");
            selector.append(currentClass);
        }

        return selector.toString();
    }

    private static Elements searchElementsInDocumentByAttributeValues(Element document, List<ElementAttribute> attributes) {
        Elements searchResults = new Elements(document.getAllElements().stream().filter(o -> elementAttributesMatch(o, attributes)).collect(Collectors.toList()));

        return searchResults;
    }

    private static boolean elementAttributesMatch(Element element, List<ElementAttribute> attributes) {
        boolean elementAttributesMatch = attributes.stream().noneMatch(o -> element.attr(o.getAttributeName()) == null
                || !element.attr(o.getAttributeName()).equals(o.getAttributeValue()));

        return elementAttributesMatch;
    }
}