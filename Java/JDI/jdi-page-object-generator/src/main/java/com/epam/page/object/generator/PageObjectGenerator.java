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
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.json.simple.parser.ParseException;

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
	 * @throws IOException If can't open JSON file or can't write java file.
	 * @throws ParseException If JSON has invalid format.
	 */
	public void generateJavaFile() throws IOException, ParseException {
		List<SearchRule> searchRules = parser.getRulesFromJSON();
		List<FieldSpec> siteClassFields = new ArrayList<>();

		for (String url : urls) {
			String pageClassName = "Page" + urlsCounter;
			ClassName pageClass = createPageClass(pageClassName, searchRules, url);

			siteClassFields.add(FieldSpec.builder(pageClass, "page" + urlsCounter++)
				.addModifiers(Modifier.PUBLIC)
				.build());
		}

		TypeSpec siteClass = TypeSpec.classBuilder("Site")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(JSite.class)
						.addMember("domain", "$S", getDomain())
						.build())
				.addFields(siteClassFields)
				.build();

		JavaFile javaFile = JavaFile.builder("com.epam.jdi.site.epam.pages", siteClass)
			.build();

		javaFile.writeTo(Paths.get(outputDir));
	}

	/**
	 * This method generates one of the nested classes with all HTML-elements found on the web-page with following url by rules
	 * @param pageClassName Name of page class.
	 * @param searchRules List of rules.
	 * @param url One of the web-pages of web-site.
	 * @return generated page class.
	 * @throws IOException If can't write java file.
	 */
	private ClassName createPageClass(String pageClassName, List<SearchRule> searchRules, String url) throws IOException {
		List<FieldSpec> fields = new ArrayList<>();

		for (SearchRule searchRule : searchRules) {
			fields.addAll(findBuilder(searchRule.getElementType()).buildField(searchRule, url));
		}

		TypeSpec pageClass = TypeSpec.classBuilder(pageClassName)
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(AnnotationSpec.builder(JPage.class)
				.addMember("url", "$S", url)
				.addMember("title", "$S", pageClassName)
				.build())
			.addFields(fields)
			.build();
		JavaFile javaFile = JavaFile.builder("com.epam.jdi.site.epam.pages", pageClass)
			.build();

		javaFile.writeTo(Paths.get(outputDir));

		return ClassName.get("com.epam.jdi.site.epam.pages", pageClassName);
	}

	/**
	 * This method searches for an appropriate builder to build nested class
	 * @param elementType Type of element that will be built.
	 * @return an appropriate builder.
	 */
	private IFieldsBuilder findBuilder(ElementType elementType) {
		return builders.stream().filter(b -> b.canBuild(elementType)).findFirst().orElseThrow(BuilderNotFoundException::new);
	}

	/**
	 * This method extracts domain URL from list of URLs of the web-site
	 * @return domain URL.
	 */
	private String getDomain() {
		return "domain";
	}

}