package com.epam.page.object.generator;

public class ElementAttribute {

	private String attributeName;
	private String attributeValue;

	public ElementAttribute() {
	}

	public ElementAttribute(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String toString() {
		return "ElementAttribute{" +
			"attributeName='" + attributeName + '\'' +
			", attributeValue='" + attributeValue + '\'' +
			'}';
	}

}