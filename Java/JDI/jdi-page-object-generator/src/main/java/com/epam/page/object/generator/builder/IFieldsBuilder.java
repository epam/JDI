package com.epam.page.object.generator.builder;

import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.FieldSpec;

import java.io.IOException;
import java.util.List;

public interface IFieldsBuilder {

	boolean canBuild(ElementType type);

	List<FieldSpec> buildField(SearchRule searchRule, String url) throws IOException;

}