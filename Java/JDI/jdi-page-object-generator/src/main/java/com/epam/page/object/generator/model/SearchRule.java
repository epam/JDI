package com.epam.page.object.generator.model;

import java.util.List;
import java.util.Objects;

public class SearchRule {

	private String tag;
	private boolean isSearchingByText;
	private List<String> classes;
	private List<ElementAttribute> attributes;

	public SearchRule() {
	}

	public SearchRule(String tag, boolean isSearchingByText, List<String> classes, List<ElementAttribute> attributes) {
		this.tag = tag;
		this.isSearchingByText = isSearchingByText;
		this.classes = classes;
		this.attributes = attributes;
	}

	public boolean isClassesEmpty() {
		return classes == null || classes.isEmpty();
	}

	public boolean isAttributesEmpty() {
		return attributes == null || attributes.isEmpty();
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isSearchingByText() {
		return isSearchingByText;
	}

	public void setSearchingByText(boolean searchingByText) {
		isSearchingByText = searchingByText;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}

	public List<ElementAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ElementAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SearchRule)) {
			return false;
		}
		SearchRule that = (SearchRule) o;

		return isSearchingByText() == that.isSearchingByText() &&
			Objects.equals(getTag(), that.getTag()) &&
			Objects.equals(getClasses(), that.getClasses()) &&
			Objects.equals(getAttributes(), that.getAttributes());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTag(), isSearchingByText(), getClasses(), getAttributes());
	}

	@Override
	public String toString() {
		return "SearchRule{" +
			"tag='" + tag + '\'' +
			", isSearchingByText=" + isSearchingByText +
			", classes=" + classes +
			", attributes=" + attributes +
			'}';
	}

}