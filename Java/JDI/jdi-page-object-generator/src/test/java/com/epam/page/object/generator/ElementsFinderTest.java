package com.epam.page.object.generator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ElementsFinderTest {

	@Test
	void Test1() {
		List<SearchRule> rules = new ArrayList<>();
		SearchRule rule1 = new SearchRule("head", true, null, null);
		List<String> urls = new ArrayList<>();
		String url1 = "https://www.google.ru";

		rules.add(rule1);
		urls.add(url1);

		try {
			if (ElementsFinder.searchElementsByRulesOnURLs(rules, urls).size() != 1) {
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}