package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.SearchRule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONIntoRuleParser {

	private static final JSONParser parser = new JSONParser();

	/**
	 * Parsing searching rules from JSON file.
	 * @param jsonPath Path to JSON file in file system.
	 * @return List of search rules from JSON file.
	 * @throws IOException If can't open JSON file.
	 * @throws ParseException If JSON has invalid format.
	 */
	public static List<SearchRule> getRulesFromJSON(String jsonPath) throws IOException, ParseException {
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
		}
	}

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

}