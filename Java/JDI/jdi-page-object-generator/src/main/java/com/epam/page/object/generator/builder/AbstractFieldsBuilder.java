package com.epam.page.object.generator.builder;

import com.epam.page.object.generator.model.SearchRule;

public abstract class AbstractFieldsBuilder implements IFieldsBuilder {

    protected String createXPathSelector(SearchRule searchRule, String element) {
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

    protected void appendClassesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.classesAreEmpty()) {
            xPathSelector.append("@class='");
            searchRule.getClasses().forEach(clazz -> xPathSelector.append(clazz).append(" "));
            xPathSelector.deleteCharAt(xPathSelector.lastIndexOf(" "));
            xPathSelector.append("' and ");
        }
    }

    protected void appendAttributesToXPath(SearchRule searchRule, StringBuilder xPathSelector) {
        if (!searchRule.attributesAreEmpty()) {
            searchRule.getAttributes().forEach(elementAttribute -> xPathSelector.append("@")
                    .append(elementAttribute.getAttributeName())
                    .append("='").append(elementAttribute.getAttributeValue()).append("'")
                    .append(" and "));
        }
    }

}