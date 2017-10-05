package com.epam.page.object.generator.parser;

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

	private static final String validJSONPath = "src/test/resources/valid.json";
	private static final String invalidJSONPath = "src/test/resources/invalid.json";

	private JSONIntoRuleParser parser;

	@Test
	void getRulesFromJSON_invalidJSONParsing() {
		parser = new JSONIntoRuleParser(validJSONPath);
		assertThrows(ParseException.class, () -> parser.getRulesFromJSON());
	}

	@Test
	void getRulesFromJSON_wrongJSONPath() {
		parser = new JSONIntoRuleParser("SomethingWentWrong.txt");
		assertThrows(IOException.class, () -> parser.getRulesFromJSON());
	}

	@Test
	void getRulesFromJSON_validJSON() throws Exception {
		List<String> classes = new ArrayList<>();
		List<ElementAttribute> attributes = new ArrayList<>();
		List<SearchRule> searchRules = new ArrayList<>();

		parser = new JSONIntoRuleParser(invalidJSONPath);
		classes.add("w3-btn");
		attributes.add(new ElementAttribute("type", "submit"));
		searchRules.add(new SearchRule("a", true, classes, Collections.emptyList()));
		searchRules.add(new SearchRule("input", false, Collections.emptyList(), attributes));

		assertEquals(searchRules, parser.getRulesFromJSON());
	}

}