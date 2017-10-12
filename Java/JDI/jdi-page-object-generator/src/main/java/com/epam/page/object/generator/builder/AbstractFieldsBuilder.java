package com.epam.page.object.generator.builder;

import com.epam.page.object.generator.model.SearchRule;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;

import org.jsoup.nodes.Element;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractFieldsBuilder implements IFieldsBuilder {

    private int abstractElementCounter;

    protected List<FieldSpec> buildAbstractField(SearchRule searchRule, String url,
        Class abstractFieldClass, String abstractFieldName) throws IOException {
        List<FieldSpec> abstractFields = new ArrayList<>();

        List<String> elements = ("text").equals(searchRule.getRequiredAttribute())
            ? searchRule.extractElementsFromWebSite(url).eachText()
            : searchRule.extractElementsFromWebSite(url).eachAttr(searchRule.getRequiredAttribute());

        for (String element : elements) {
            abstractFields.add(FieldSpec.builder(abstractFieldClass, getFieldName(element))
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(FindBy.class)
                    .addMember("xpath", "$S", createXPathSelector(searchRule, element))
                    .build())
                .build());
        }

        return abstractFields;
    }

    private String createXPathSelector(SearchRule searchRule, String element) {
        StringBuilder xPathSelector = new StringBuilder();

        xPathSelector.append("//").append(searchRule.getTag()).append("[");

        appendClassesToXPath(searchRule, xPathSelector);
        appendAttributesToXPath(searchRule, xPathSelector);

        if (("text").equals(searchRule.getRequiredAttribute())) {
            xPathSelector.append("text()");
        } else {
            xPathSelector.append("@").append(searchRule.getRequiredAttribute());
        }

        xPathSelector.append("='").append(element).append("']");

        return xPathSelector.toString();
    }

    private void appendClassesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.classesAreEmpty()) {
            xPathSelector.append("@class='");
            searchRule.getClasses().forEach(clazz -> xPathSelector.append(clazz).append(" "));
            xPathSelector.deleteCharAt(xPathSelector.lastIndexOf(" "));
            xPathSelector.append("' and ");
        }
    }

    private void appendAttributesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.attributesAreEmpty()) {
            searchRule.getAttributes().forEach(elementAttribute -> xPathSelector.append("@")
                    .append(elementAttribute.getAttributeName())
                    .append("='").append(elementAttribute.getAttributeValue()).append("'")
                    .append(" and "));
        }
    }

    private String getFieldName(String element) {
        String fieldName = element.toString().replaceAll("[^A-Za-z0-9]", "");
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

        return fieldName;
    }

}