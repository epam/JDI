package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import java.io.IOException;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ButtonFieldsBuilder extends AbstractFieldsBuilder {

	@Override
	public boolean canBuild(ElementType type) {
		return type.equals(ElementType.BUTTON);
	}

	@Override
	public List<FieldSpec> buildField(SearchRule searchRule, String url) throws IOException {
		int buttonCounter = 0;
		List<FieldSpec> buttonFields = new ArrayList<>();

		List<String> elements = ("text").equals(searchRule.getRequiredAttribute())
				? searchRule.extractElementsFromWebSite(url).eachText()
				: searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

		for (String element : elements) {
			buttonFields.add(FieldSpec.builder(Button.class, "button" + buttonCounter++)
					.addModifiers(Modifier.PUBLIC)
					.addAnnotation(AnnotationSpec.builder(FindBy.class)
							.addMember("xpath", "$S", createXPathSelector(searchRule, element))
							.build())
					.build());
		}

		return buttonFields;
	}

}