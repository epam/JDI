package com.epam.page.object.generator.builder.annotation;

import com.epam.page.object.generator.model.AnnotationDescription;
import com.squareup.javapoet.AnnotationSpec;

public class ButtonAnnotationBuilder implements IAnnotationBuilder {

	@Override
	public AnnotationSpec buildAnnotation(Class annotationClass, AnnotationDescription annotationDescription) {
		return AnnotationSpec.builder(annotationClass)
				.addMember(annotationDescription.getName(), "$S", annotationDescription.getArgs())
				.build();
	}
}