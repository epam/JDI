package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.FieldSpec;
import java.io.IOException;
import java.util.List;

public class ImageFieldsBuilder extends AbstractFieldsBuilder {

	@Override
	public boolean canBuild(ElementType type) {
		return type.equals(ElementType.IMAGE);
	}

	@Override
	public List<FieldSpec> buildField(SearchRule searchRule, String url) throws IOException {
		return buildAbstractField(searchRule, url, Image.class, "image");
	}

}