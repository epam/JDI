package com.epam.page.object.generator;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestThisShit {

	private static final Logger logger = LogManager.getLogger(TestThisShit.class);

    public static void main(String[] args) {
        List<SearchRule> rules = new ArrayList<>();

//        SearchRule rule1 = new SearchRule("a", true, null, null);

        String class1 = "testclass1";
        String class2 = "testclass2";

        List<String> classes = new ArrayList<>();

        classes.add(class1);
        classes.add(class2);

//        SearchRule rule2 = new SearchRule(null, true, classes2, null);

        List<ElementAttribute> attributes = new ArrayList<>();
        ElementAttribute attribute1 = new ElementAttribute("attr1", "attr1value");
        ElementAttribute attribute2 = new ElementAttribute("attr2", "attr2value");
        attributes.add(attribute1);
        attributes.add(attribute2);


        SearchRule rule3 = new SearchRule("b", true, classes, attributes);

        rules.add(rule3);

        List<String> urls = new ArrayList<>();
        urls.add("https://www.google.ru");

        try {
            ElementsFinder.searchElementsByRulesOnURLs(rules, urls);
        } catch (Exception ex) {
			logger.error(ex);
        }
    }
}