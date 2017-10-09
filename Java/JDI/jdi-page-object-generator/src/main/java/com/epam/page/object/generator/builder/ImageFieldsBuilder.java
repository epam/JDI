package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.openqa.selenium.support.FindBy;

public class ImageFieldsBuilder extends AbstractFieldsBuilder {

	@Override
	public boolean canBuild(ElementType type) {
		return type.equals(ElementType.IMAGE);
	}

	@Override
	public List<FieldSpec> buildField(SearchRule searchRule, String url) throws IOException {
		int imageCounter = 0;
		List<FieldSpec> imageFields = new ArrayList<>();

		List<String> elements = searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

		for (String element : elements) {
			imageFields.add(FieldSpec.builder(Image.class, "image" + imageCounter++)
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(FindBy.class)
					.addMember("xpath", "$S", createXPathSelector(searchRule, element))
					.build())
				.build());
		}

		return imageFields;
	}
}