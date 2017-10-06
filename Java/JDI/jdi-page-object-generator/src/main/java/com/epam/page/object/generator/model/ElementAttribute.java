package com.epam.page.object.generator.model;

public class ElementAttribute {

	private String attributeName;
	private String attributeValue;

	public ElementAttribute(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

}