package com.epam.page.object.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class PageObjectGeneratorTest {

	private static final String validJSONPath = "src/test/resources/validJSON.txt";

	@Test
	void mainTest() throws IOException, ParseException {
		List<String> urls = new ArrayList<>();

		urls.add("https://www.w3schools.com/html/html_forms.asp");

		PageObjectGenerator pageObjectGenerator = new PageObjectGenerator(validJSONPath, urls, "src/test/resources/MainPage.java");

		try {
			pageObjectGenerator.generateJavaFile();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}