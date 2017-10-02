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

	public boolean isSearchingByText() {
		return isSearchingByText;
	}

	public List<String> getClasses() {
		return classes;
	}

	public List<ElementAttribute> getAttributes() {
		return attributes;
	}
}