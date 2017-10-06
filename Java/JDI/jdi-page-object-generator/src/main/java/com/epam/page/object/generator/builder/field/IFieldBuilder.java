package com.epam.page.object.generator.builder.field;

import com.epam.page.object.generator.builder.annotation.IAnnotationBuilder;
import com.epam.page.object.generator.model.ElementType;
import com.squareup.javapoet.FieldSpec;

public interface IFieldBuilder {

	boolean canBuild(ElementType type);

	FieldSpec buildField(String fieldName, IAnnotationBuilder annotationBuilder);

}