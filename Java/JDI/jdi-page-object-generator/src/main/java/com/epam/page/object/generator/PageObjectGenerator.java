package com.epam.page.object.generator;

import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.page.object.generator.builder.ButtonFieldsBuilder;
import com.epam.page.object.generator.builder.IFieldsBuilder;
import com.epam.page.object.generator.builder.TextFieldsBuilder;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.epam.page.object.generator.parser.JSONIntoRuleParser;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.json.simple.parser.ParseException;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PageObjectGenerator {

	private JSONIntoRuleParser parser;
	private List<String> urls;
	private String outputDir;

	private int urlsCounter;
	private List<IFieldsBuilder> builders = new ArrayList<>();

	public PageObjectGenerator(String jsonPath, List<String> urls, String outputDir) {
		parser = new JSONIntoRuleParser(jsonPath);
		this.urls = urls;
		this.outputDir = outputDir;
		builders.add(new ButtonFieldsBuilder());
		builders.add(new TextFieldsBuilder());
	}

	/**
	 * This method generates Java-file with all HTML-elements found on the web-site  by rules given by user in .json
	 * @throws IOException If can't open JSON file.
	 * @throws ParseException If JSON has invalid format.
	 */
	public void generateJavaFile() throws IOException, ParseException {
		List<TypeSpec> pageClasses = new ArrayList<>();
		List<SearchRule> searchRules = parser.getRulesFromJSON();

		for (String url : urls) {
			pageClasses.add(createPageClass(searchRules, url));
		}

		TypeSpec siteClass = TypeSpec.classBuilder("Site")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(JSite.class)
						.addMember("domain", "$S", getDomain())
						.build())
				.addTypes(pageClasses)
				.build();

		JavaFile javaFile = JavaFile.builder("com.epam.jdi.site.epam.pages", siteClass)
			.build();

		javaFile.writeTo(Paths.get(outputDir));
	}

	/**
	 * This method generates one of the nested classes with all HTML-elements found on the web-page with following url by rules
	 * @param searchRules is list of rules
	 * @param url is one of the web-pages of web-site
	 * @return a description of a nested class for one of the pages of the web-site
	 */
	private TypeSpec createPageClass(List<SearchRule> searchRules, String url) {
		String name = "Page" + urlsCounter++;
		List<FieldSpec> fields = new ArrayList<>();

		for (SearchRule searchRule : searchRules) {
			fields.addAll(findBuilder(searchRule.getElementType()).buildField(searchRule, url));
		}

		return TypeSpec.classBuilder(name)
				.addAnnotation(AnnotationSpec.builder(JPage.class)
						.addMember("url", "$S", url)
						.addMember("title", "$S", name)
						.build())
				.addFields(fields)
				.build();
	}

	/**
	 * This method searches for an appropriate builder to build nested class
	 * @param elementType Type of element that will be built.
	 * @return an appropriate builder
	 */
	private IFieldsBuilder findBuilder(ElementType elementType) {
		return builders.stream().filter(b -> b.canBuild(elementType)).findFirst().orElseThrow(BuilderNotFoundException::new);
	}

	/**
	 * This method extracts domain URL from list of URLs of the web-site
	 * @return domain URL
	 */
	private String getDomain() {
		return "domain";
	}

}