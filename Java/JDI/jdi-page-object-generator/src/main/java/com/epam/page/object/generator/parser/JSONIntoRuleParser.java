package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.model.SearchRule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONIntoRuleParser {

	private String jsonPath;
	private final JSONParser parser = new JSONParser();
	private List<IRuleParser> parsers = new ArrayList<>();

	public JSONIntoRuleParser(String jsonPath) {
		this.jsonPath = jsonPath;
		parsers.add(new ButtonRuleParser());
	}

	/**
	 * Parsing searching rules from JSON file.
	 * @return List of search rules from JSON file.
	 * @throws IOException If can't open JSON file.
	 * @throws ParseException If JSON has invalid format.
	 * @throws ClassNotFoundException If there is not parser for such type.
	 */
	public List<SearchRule> getRulesFromJSON() throws IOException, ParseException, ClassNotFoundException {
		try (BufferedReader br = new BufferedReader(new FileReader(jsonPath))) {
			JSONObject fullJSON = (JSONObject) parser.parse(br);
			JSONArray elements = (JSONArray) fullJSON.get("elements");
			List<SearchRule> searchRules = new ArrayList<>();

			for (Object element : elements) {
				JSONObject jsonObject = (JSONObject) element;
				String type = (String) jsonObject.get("type");

				searchRules.add(findParser(type).parse(jsonObject));
			}

			return searchRules;
		}
	}

	private IRuleParser findParser(String type) throws ClassNotFoundException {
		return parsers.stream().filter(p -> p.canParse(type)).findFirst().orElseThrow(ClassNotFoundException::new);
	}

}