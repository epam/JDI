package com.epam.page.object.generator.builder.field;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.page.object.generator.builder.annotation.IAnnotationBuilder;
import com.epam.page.object.generator.model.AnnotationDescription;
import com.epam.page.object.generator.model.ElementType;
import com.squareup.javapoet.FieldSpec;
import javax.lang.model.element.Modifier;
import org.openqa.selenium.support.FindBy;

public class ButtonFieldBuilder implements IFieldBuilder {

	@Override
	public boolean canBuild(ElementType type) {
		return type.equals(ElementType.BUTTON);
	}

	@Override
	public FieldSpec buildField(String fieldName, IAnnotationBuilder annotationBuilder) {
		return FieldSpec.builder(Button.class, fieldName)
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(annotationBuilder.buildAnnotation(FindBy.class, new AnnotationDescription("", "")))
			.build();
	}

}