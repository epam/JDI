package com.epam.page.object.generator;

import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.page.object.generator.builder.ButtonFieldsBuilder;
import com.epam.page.object.generator.builder.IFieldsBuilder;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.parser.JSONIntoRuleParser;
import com.epam.page.object.generator.model.SearchRule;
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
	}

	public void generateJavaFile() throws IOException, ParseException, IllegalArgumentException, ClassNotFoundException {
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

	private TypeSpec createPageClass(List<SearchRule> searchRules, String url) throws ClassNotFoundException {
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

	private IFieldsBuilder findBuilder(ElementType elementType) throws ClassNotFoundException {
		return builders.stream().filter(b -> b.canBuild(elementType)).findFirst().orElseThrow(ClassNotFoundException::new);
	}

	private String getDomain() {
		return "domain";
	}

}