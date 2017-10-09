package com.epam.page.object.generator.builder;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import org.openqa.selenium.support.FindBy;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

public class CheckBoxFieldsBuilder extends AbstractFieldsBuilder {
    @Override
    public boolean canBuild(ElementType type) {
        return (ElementType.CHECKBOX).equals(type);
    }

    @Override
    public List<FieldSpec> buildField(SearchRule searchRule, String url) {
        int checkBoxCounter = 0;
        List<FieldSpec> checkBoxFields = new ArrayList<>();

        List<String> elements = ("text").equals(searchRule.getRequiredAttribute())
                ? searchRule.extractElementsFromWebSite(url).eachText()
                : searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

        for (String element : elements) {
            checkBoxFields.add(FieldSpec.builder(CheckBox.class, "checkBox" + checkBoxCounter++)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(AnnotationSpec.builder(FindBy.class)
                            .addMember("xpath", "$S", createXPathSelector(searchRule, element))
                            .build())
                    .build());
        }

        return checkBoxFields;
    }
}
