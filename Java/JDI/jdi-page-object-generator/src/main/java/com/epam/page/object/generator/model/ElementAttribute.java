package com.epam.page.object.generator.model;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ElementAttribute)) {
			return false;
		}
		ElementAttribute that = (ElementAttribute) o;

		return Objects.equals(getAttributeName(), that.getAttributeName()) &&
			Objects.equals(getAttributeValue(), that.getAttributeValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAttributeName(), getAttributeValue());
	}

	@Override
	public String toString() {
		return "ElementAttribute{" +
			"attributeName='" + attributeName + '\'' +
			", attributeValue='" + attributeValue + '\'' +
			'}';
	}

}