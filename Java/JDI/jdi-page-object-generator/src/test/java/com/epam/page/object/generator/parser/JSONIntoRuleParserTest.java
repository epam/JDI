package com.epam.page.object.generator.parser;

import static com.epam.page.object.generator.parser.JSONIntoRuleParser.getRulesFromJSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.SearchRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class JSONIntoRuleParserTest {

	private static final String validJSONPath = "src/test/resources/validJSON.txt";
	private static final String invalidJSONPath = "src/test/resources/invalidJSON.txt";

	@Test
	void getRulesFromJSON_invalidJSONParsing() {
		assertThrows(ParseException.class, () -> getRulesFromJSON(invalidJSONPath));
	}

	@Test
	void getRulesFromJSON_wrongJSONPath() {
		assertThrows(IOException.class, () -> getRulesFromJSON("SomethingWentWrong.txt"));
	}

	@Test
	void getRulesFromJSON_validJSON() throws IOException, ParseException {
		List<String> classes = new ArrayList<>();
		List<ElementAttribute> attributes = new ArrayList<>();
		List<SearchRule> searchRules = new ArrayList<>();

		classes.add("w3-btn");
		attributes.add(new ElementAttribute("type", "submit"));
		searchRules.add(new SearchRule("a", true, classes, Collections.emptyList()));
		searchRules.add(new SearchRule("input", false, Collections.emptyList(), attributes));

		assertEquals(searchRules, getRulesFromJSON(validJSONPath));
	}

}