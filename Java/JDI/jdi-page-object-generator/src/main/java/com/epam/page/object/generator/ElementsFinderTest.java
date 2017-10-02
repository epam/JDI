package com.epam.page.object.generator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ElementsFinderTest {
    @Test
    void Test1() {
        List<SearchRule> rules = new ArrayList<>();

        SearchRule rule1 = new SearchRule("head", true, null, null);
        rules.add(rule1);

        List<String> urls = new ArrayList<>();

        String url1 = "https://www.google.ru";
        urls.add(url1);

        try {
            if (ElementsFinder.searchElementsByRulesOnURLs(rules, urls).size() != 1)
                Assert.fail();
//            Assertions.assertEquals(1, ElementsFinder.searchElementsByRulesOnURLs(rules, urls).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void Test2() {
        SearchRule rule = new SearchRule("div", true, null, null);
    }

    @Test
    void Test3() {
    }

    @Test
    void Test4() {
    }

    @Test
    void Test5() {
    }
}