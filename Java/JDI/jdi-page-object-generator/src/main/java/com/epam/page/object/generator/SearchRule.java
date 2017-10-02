package com.epam.page.object.generator;

import java.util.List;

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
	public String toString() {
		return "SearchRule{" +
			"tag='" + tag + '\'' +
			", isSearchingByText=" + isSearchingByText +
			", classes=" + classes +
			", attributes=" + attributes +
			'}';
	}

}