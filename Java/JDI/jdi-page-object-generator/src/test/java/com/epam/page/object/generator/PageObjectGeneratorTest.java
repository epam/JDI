package com.epam.page.object.generator;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PageObjectGeneratorTest {

	private static final String validJSONPath = "src/test/resources/valid.json";

	@Test
	void mainTest() throws Exception {
		List<String> urls = new ArrayList<>();

		urls.add("https://www.w3schools.com/html/html_forms.asp");

		PageObjectGenerator pageObjectGenerator = new PageObjectGenerator(validJSONPath, urls, "src/test/resources/MainPage.java");

		pageObjectGenerator.generateJavaFile();
	}

}