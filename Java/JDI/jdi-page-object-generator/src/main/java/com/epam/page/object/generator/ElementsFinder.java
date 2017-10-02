package com.epam.page.object.generator;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ElementsFinder {

	private static final Logger logger = LogManager.getLogger(ElementsFinder.class);

    public static Map<SearchRule, Elements> searchElementsByRulesOnURLs(List<SearchRule> rules, List<String> urls) {
        Map<SearchRule, Elements> searchResults = new HashMap<>();
		Document currentDocument = null;

        for (String currentURL : urls) {
			try {
				currentDocument = Jsoup.connect(currentURL).get();
			} catch (IOException ex) {
				logger.error(ex);
			}

            for (SearchRule currentRule : rules) {
                searchResults.put(currentRule, searchElementsByRuleOnURL(currentRule, currentDocument));
            }
        }

        return searchResults;
    }

    private static Elements searchElementsByRuleOnURL(SearchRule rule, Document document) {
		Elements resultsOfSearchByTag = new Elements();
		Elements resultsOfSearchByClasses = new Elements();
		Elements resultsOfSearchByAttributes = new Elements();

        // For offline mode:
//        String html = "<p>An <a href='http://example.com/'><b class=\"testclass1 testclass2\" attr1=\"attr1value\" attr2=\"attr2value\">example</b></a> link.</p>";
//        Document document = Jsoup.parse(html);

		if (document != null) {
			if (rule.getTag() != null) {
				resultsOfSearchByTag = document.select(rule.getTag());
			}

			if (!rule.getClasses().isEmpty()) {
				resultsOfSearchByClasses = document.select(prepareCSSQuerySelectorByClasses(rule.getClasses()));
				resultsOfSearchByTag.retainAll(resultsOfSearchByClasses);
			}

			if (!rule.getAttributes().isEmpty()) {
				resultsOfSearchByAttributes = searchElelemntsInDocumentByAttributeValues(document, rule.getAttributes());
				resultsOfSearchByTag.retainAll(resultsOfSearchByAttributes);
			}
		}

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