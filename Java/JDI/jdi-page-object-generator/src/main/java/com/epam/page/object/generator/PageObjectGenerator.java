package com.epam.page.object.generator;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.page.object.generator.builder.ButtonFieldsBuilder;
import com.epam.page.object.generator.builder.CheckBoxFieldsBuilder;
import com.epam.page.object.generator.builder.IFieldsBuilder;
import com.epam.page.object.generator.builder.ImageFieldsBuilder;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageObjectGenerator {

	private static final String PACKAGE_FOR_GENERATED_PAGES = "com.epam.jdi.site.epam.pages";
	private static final String PACKAGE_FOR_GENERATED_SITE = "com.epam.jdi.site.epam.site";

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
		builders.add(new CheckBoxFieldsBuilder());
		builders.add(new ImageFieldsBuilder());
	}

	/**
	 * Generates .java file with all HTML-elements found on the web-site by rules given by user in .json file.
	 * @throws IOException If .json file could not be opened or written to .java file.
	 * @throws ParseException If JSON has invalid format.
	 * @throws URISyntaxException If urls could not be parsed as URI references.
	 */
	public void generateJavaFile() throws IOException, ParseException, URISyntaxException {
		List<SearchRule> searchRules = parser.getRulesFromJSON();
		List<FieldSpec> siteClassFields = new ArrayList<>();

		for (String url : urls) {
			String pageClassName = getPageTitle(url);
			String pageFieldName = getFieldName(url);
			ClassName pageClass = createPageClass(pageClassName, searchRules, url);

			siteClassFields.add(FieldSpec.builder(pageClass, pageFieldName)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addAnnotation(AnnotationSpec.builder(JPage.class)
					.addMember("url", "$S", getUrlWithoutDomain(url))
					.addMember("title", "$S", getPageTitle(url))
					.build())
				.build());
		}

		TypeSpec siteClass = TypeSpec.classBuilder("Site")
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(AnnotationSpec.builder(JSite.class)
				.addMember("domain", "$S", getDomainName())
				.build())
			.superclass(WebSite.class)
			.addFields(siteClassFields)
			.build();

		JavaFile javaFile = JavaFile.builder(PACKAGE_FOR_GENERATED_SITE, siteClass)
			.build();

		javaFile.writeTo(Paths.get(outputDir));
	}

	/**
	 * Generates one of the nested classes with all HTML-elements found on the web-page with following url by rules.
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
			.superclass(WebPage.class)
			.addFields(fields)
			.build();
		JavaFile javaFile = JavaFile.builder(PACKAGE_FOR_GENERATED_PAGES, pageClass)
			.build();

		javaFile.writeTo(Paths.get(outputDir));

		return ClassName.get(PACKAGE_FOR_GENERATED_PAGES, pageClassName);
	}

	/**
	 * Searches for an appropriate builder for input element.
	 * @param elementType Type of element that will be built.
	 * @return an appropriate builder.
	 */
	private IFieldsBuilder findBuilder(ElementType elementType) {
		return builders.stream().filter(b -> b.canBuild(elementType)).findFirst().orElseThrow(BuilderNotFoundException::new);
	}

	/**
	 * Returns URL without it's domain part.
	 * @param url One of the web-pages of web-site.
	 * @return URL without domain part.
	 * @throws URISyntaxException If url could not be parsed as a URI reference.
	 */
	private String getUrlWithoutDomain(String url) throws URISyntaxException {
		URI uri = new URI(url);

		return uri.getPath();
	}

	/**
	 * Extracts domain URL from list of URLs of the web-site.
	 * @return domain URL.
	 * @throws URISyntaxException If url could not be parsed as a URI reference.
	 */
	private String getDomainName() throws URISyntaxException {
		URI uri = new URI(urls.get(0));

		return uri.getHost();
	}

	private String getPageTitle(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		String title = document.title().replaceAll("[^A-Za-z0-9]", "");
		title = title.substring(0, 1).toUpperCase() + title.substring(1);

		return title;
	}

	private String getFieldName(String url) throws IOException {
		Document document = Jsoup.connect(url).get();

		String title = document.title().replaceAll("[^A-Za-z0-9]", "");
		title = title.substring(0, 1).toLowerCase() + title.substring(1);

		return title;
	}

}