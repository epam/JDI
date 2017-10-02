package com.epam.page.object.generator;

import static com.epam.page.object.generator.parser.JSONIntoRuleParser.getRulesFromJSON;

import com.epam.page.object.generator.finder.ElementsFinder;
import com.epam.page.object.generator.model.SearchRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.ParseException;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

class PageObjectGeneratorTest {

	private static final String validJSONPath = "src/test/resources/validJSON.txt";

	@Test
	void mainTest() throws IOException, ParseException {
		List<SearchRule> searchRules = getRulesFromJSON(validJSONPath);
		List<String> urls = new ArrayList<>();

		urls.add("https://www.w3schools.com/html/html_forms.asp");

		Map<SearchRule, Elements> searchRuleElementsMap = ElementsFinder.searchElementsByRulesOnURLs(searchRules, urls);

		for (SearchRule searchRule : searchRuleElementsMap.keySet()) {
			System.out.println(searchRule + ": ");
			List<String> resultList = searchRule.isSearchingByText()
				? searchRuleElementsMap.get(searchRule).eachText()
				: searchRuleElementsMap.get(searchRule).eachAttr("value");

			for (String element : resultList) {
				System.out.println(element);
			}
		}
	}

}