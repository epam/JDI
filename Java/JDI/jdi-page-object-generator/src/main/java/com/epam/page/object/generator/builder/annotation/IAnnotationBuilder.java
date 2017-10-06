package com.epam.page.object.generator.builder.annotation;

import com.epam.page.object.generator.model.AnnotationDescription;
import com.squareup.javapoet.AnnotationSpec;

public interface IAnnotationBuilder {

	AnnotationSpec buildAnnotation(Class annotationClass, AnnotationDescription annotationDescription);

}