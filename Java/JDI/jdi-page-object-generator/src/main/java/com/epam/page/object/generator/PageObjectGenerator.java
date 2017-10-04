package com.epam.page.object.generator;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.page.object.generator.finder.ElementsFinder;
import com.epam.page.object.generator.model.SearchRule;
import com.epam.page.object.generator.parser.JSONIntoRuleParser;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Modifier;
import org.json.simple.parser.ParseException;
import org.jsoup.select.Elements;
import org.openqa.selenium.support.FindBy;

public class PageObjectGenerator {

	private String jsonPath;
	private List<String> urls;
	private String outputDir;

	private StringBuilder ruleDescription;
	private List<FieldSpec> fields;

	public PageObjectGenerator(String jsonPath, List<String> urls, String outputDir) {
		this.jsonPath = jsonPath;
		this.urls = urls;
		this.outputDir = outputDir;
		ruleDescription = new StringBuilder();
		fields = new ArrayList<>();
	}

	public void generateJavaFile() throws IOException, ParseException, IllegalArgumentException {
		List<SearchRule> searchRules = JSONIntoRuleParser.getRulesFromJSON(jsonPath);
		Map<SearchRule, Elements> searchResultsMap = ElementsFinder.searchElementsByRulesOnURLs(searchRules, urls);

		addAllButtonsAsFields(searchResultsMap);

		TypeSpec mainPage = TypeSpec.classBuilder("MainPage")
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(AnnotationSpec.builder(JPage.class)
				.addMember("url", "$S", urls.get(0))
				.addMember("title", "$S", "Main Page")
				.build())
			.addFields(fields)
			.build();

		JavaFile javaFile = JavaFile.builder("com.epam.jdi.site.epam.pages", mainPage)
			.build();

		javaFile.writeTo(Paths.get(outputDir));
	}

	private void addAllButtonsAsFields(Map<SearchRule, Elements> searchResultsMap) {
		// TODO: find only buttons in map

		int elementCounter = 0;

		for (SearchRule searchRule : searchResultsMap.keySet()) {
			List<String> resultList = searchRule.isSearchingByText()
				? searchResultsMap.get(searchRule).eachText()
				: searchResultsMap.get(searchRule).eachAttr("value");

			for (String element : resultList) {
				String name = searchRule.getTag() + elementCounter;

				createXPathSelectorForButton(searchRule, element);
				fields.add(makeButtonField(name));
				elementCounter++;
			}
		}
	}

	private void createXPathSelectorForButton(SearchRule searchRule, String element) {
		ruleDescription = new StringBuilder();

		ruleDescription.append("//");
		ruleDescription.append(searchRule.getTag());
		ruleDescription.append("[");

		appendButtonClassesToXPath(searchRule);
		appendButtonAttributesToXPath(searchRule);

		if (searchRule.isSearchingByText()) {
			ruleDescription.append("text()");
		} else {
			ruleDescription.append("@value");
		}

		ruleDescription.append("='").append(element).append("']");
	}

	private void appendButtonClassesToXPath(SearchRule searchRule) {
		if (!searchRule.isClassesEmpty()) {
			ruleDescription.append("[@class='");
			searchRule.getClasses().forEach(clazz -> ruleDescription.append(clazz).append(" "));
			ruleDescription.deleteCharAt(ruleDescription.lastIndexOf(" "));
			ruleDescription.append("' and ");
		}
	}

	private void appendButtonAttributesToXPath(SearchRule searchRule) {
		if (!searchRule.isAttributesEmpty()) {
			searchRule.getAttributes().forEach(elementAttribute -> ruleDescription.append("@")
				.append(elementAttribute.getAttributeName())
				.append("='").append(elementAttribute.getAttributeValue()).append("'")
				.append(" and "));
		}
	}

	private FieldSpec makeButtonField(String buttonName) {
		return FieldSpec.builder(Button.class, buttonName)
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(AnnotationSpec.builder(FindBy.class)
				.addMember("xpath", "$S", ruleDescription)
				.build())
			.build();
	}

}