package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ButtonFieldsBuilder implements IFieldsBuilder {

	@Override
	public boolean canBuild(ElementType type) {
		return type.equals(ElementType.BUTTON);
	}

	@Override
	public List<FieldSpec> buildField(SearchRule searchRule, String url) {
		int buttonCounter = 0;
		List<FieldSpec> buttonFields = new ArrayList<>();

		List<String> elements = ("text").equals(searchRule.getRequiredAttribute())
				? searchRule.extractElementsFromWebSite(url).eachText()
				: searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

		for (String element : elements) {
			buttonFields.add(FieldSpec.builder(Button.class, "button" + buttonCounter++)
					.addModifiers(Modifier.PUBLIC)
					.addAnnotation(AnnotationSpec.builder(FindBy.class)
							.addMember("xpath", "$S", createXPathSelectorForButton(searchRule, element))
							.build())
					.build());
		}

		return buttonFields;
	}
	
	private String createXPathSelectorForButton(SearchRule searchRule, String element) {
		StringBuilder xPathSelector = new StringBuilder();
		
		xPathSelector.append("//").append(searchRule.getTag()).append("[");

		appendButtonClassesToXPath(searchRule, xPathSelector);
		appendButtonAttributesToXPath(searchRule, xPathSelector);

		if (("text").equals(searchRule.getRequiredAttribute())) {
			xPathSelector.append("text()");
		} else {
			xPathSelector.append("@").append(searchRule.getRequiredAttribute());
		}

		xPathSelector.append("='").append(element).append("']");

		return xPathSelector.toString();
	}

	private void appendButtonClassesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
		if (!searchRule.classesAreEmpty()) {
			xPathSelector.append("@class='");
			searchRule.getClasses().forEach(clazz -> xPathSelector.append(clazz).append(" "));
			xPathSelector.deleteCharAt(xPathSelector.lastIndexOf(" "));
			xPathSelector.append("' and ");
		}
	}

	private void appendButtonAttributesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
		if (!searchRule.attributesAreEmpty()) {
			searchRule.getAttributes().forEach(elementAttribute -> xPathSelector.append("@")
				.append(elementAttribute.getAttributeName())
				.append("='").append(elementAttribute.getAttributeValue()).append("'")
				.append(" and "));
		}
	}

}