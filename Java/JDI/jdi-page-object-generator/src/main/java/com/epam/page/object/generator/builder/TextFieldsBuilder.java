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

public class TextFieldsBuilder implements IFieldsBuilder {

    @Override
    public boolean canBuild(ElementType type) {
        return (ElementType.TEXT).equals(type);
    }

    @Override
    public List<FieldSpec> buildField(SearchRule searchRule, String url) {
        int textCounter = 0;
        List<FieldSpec> textFields = new ArrayList<>();

        List<String> elements = searchRule.isSearchingText()
                ? searchRule.extractElementsFromWebSite(url).eachText()
                : searchRule.extractElementsFromWebSite(url).eachAttr("value");

        for (String element : elements) {
            textFields.add(FieldSpec.builder(Text.class, "text" + textCounter++)
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(AnnotationSpec.builder(FindBy.class)
                            .addMember("xpath", "$S", createXPathSelectorForText(searchRule, element))
                            .build())
                    .build());
        }

        return textFields;
    }

    private String createXPathSelectorForText(SearchRule searchRule, String element) {
        StringBuilder xPathSelector = new StringBuilder();

        xPathSelector.append("//").append(searchRule.getTag()).append("[");

        appendTextClassesToXPath(searchRule, xPathSelector);
        appendTextAttributesToXPath(searchRule, xPathSelector);

        if (searchRule.isSearchingText()) {
            xPathSelector.append("text()");
        } else {
            xPathSelector.append("@value");
        }

        xPathSelector.append("='").append(element).append("']");

        return xPathSelector.toString();
    }

    private void appendTextClassesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.classesAreEmpty()) {
            xPathSelector.append("[@class='");
            searchRule.getClasses().forEach(clazz -> xPathSelector.append(clazz).append(" "));
            xPathSelector.deleteCharAt(xPathSelector.lastIndexOf(" "));
            xPathSelector.append("' and ");
        }
    }

    private void appendTextAttributesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.attributesAreEmpty()) {
            searchRule.getAttributes().forEach(elementAttribute -> xPathSelector.append("@")
                    .append(elementAttribute.getAttributeName())
                    .append("='").append(elementAttribute.getAttributeValue()).append("'")
                    .append(" and "));
        }
    }
}
