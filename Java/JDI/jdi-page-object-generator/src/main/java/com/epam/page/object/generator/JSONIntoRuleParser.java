package com.epam.page.object.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.select.Elements;

public class JSONIntoRuleParser {

	private static final Logger logger = LogManager.getLogger(JSONIntoRuleParser.class);

	private static final JSONParser parser = new JSONParser();

	private static SearchRule getRuleFromButton(String name, String rules) {
		SearchRule searchRule = new SearchRule();
		String[] attributes = rules.split(";");
		List<String> classes = new ArrayList<>();
		List<ElementAttribute> elementAttributes = new ArrayList<>();

		if (name.equals("text")) {
			searchRule.setSearchingByText(true);
		} else {
			searchRule.setSearchingByText(false);
		}

		for (String attribute : attributes) {
			String[] singleAttribute = attribute.split("=");

			switch (singleAttribute[0]) {
				case "tag":
					searchRule.setTag(singleAttribute[1]);
					break;
				case "class":
					classes.add(singleAttribute[1]);
					break;
				default:
					elementAttributes.add(new ElementAttribute(singleAttribute[0], singleAttribute[1]));
					break;
			}
		}

		searchRule.setClasses(classes);
		searchRule.setAttributes(elementAttributes);

		return searchRule;
	}

	public static List<SearchRule> getRulesFromJSON(String jsonPath) {
		try (BufferedReader br = new BufferedReader(new FileReader(jsonPath))) {
			JSONObject fullJSON = (JSONObject) parser.parse(br);
			JSONArray elements = (JSONArray) fullJSON.get("elements");
			List<SearchRule> searchRules = new ArrayList<>();

			for (Object element : elements) {
				JSONObject jsonElement = (JSONObject) element;
				String type = (String) jsonElement.get("type");

				switch (type) {
					case "Button":
						String name = (String) jsonElement.get("name");
						String rules = (String) jsonElement.get("rules");
						searchRules.add(getRuleFromButton(name, rules));
						break;
					default:
						break;
				}
			}

			return searchRules;
		} catch (IOException | ParseException ex) {
			logger.error(ex);
		}

		return Collections.emptyList();
	}

	public static void main(String[] args) {
		List<SearchRule> searchRules
			= getRulesFromJSON("/Users/Alexander/Projects/JDI/Java/JDI/jdi-page-object-generator/src/main/resources/test.txt");
		List<String> urls = new ArrayList<>();

		urls.add("https://www.w3schools.com/html/html_forms.asp");

		Map<SearchRule, Elements> searchRuleElementsMap = ElementsFinder.searchElementsByRulesOnURLs(searchRules, urls);

		for (SearchRule searchRule : searchRuleElementsMap.keySet()) {
			logger.info(searchRule + ": ");

			List<String> resultList = searchRule.isSearchingByText()
				? searchRuleElementsMap.get(searchRule).eachText()
				: searchRuleElementsMap.get(searchRule).eachAttr("value");

			for (String element : resultList) {
				logger.info(element);
			}
		}
	}

}