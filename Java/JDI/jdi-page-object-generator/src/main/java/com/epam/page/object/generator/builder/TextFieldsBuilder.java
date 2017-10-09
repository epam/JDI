package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TextFieldsBuilder extends AbstractFieldsBuilder {

    @Override
    public boolean canBuild(ElementType type) {
        return (ElementType.TEXT).equals(type);
    }

    @Override
    public List<FieldSpec> buildField(SearchRule searchRule, String url) {
        int textCounter = 0;
        List<FieldSpec> textFields = new ArrayList<>();

        List<String> elements = ("text").equals(searchRule.getRequiredAttribute())
                ? searchRule.extractElementsFromWebSite(url).eachText()
                : searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

        for (String element : elements) {
            textFields.add(FieldSpec.builder(Text.class, "text" + textCounter++)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(AnnotationSpec.builder(FindBy.class)
                            .addMember("xpath", "$S", createXPathSelector(searchRule, element))
                            .build())
                    .build());
        }

        return textFields;
    }
}
