package com.epam.page.object.generator;

import java.util.List;
import lombok.Value;

@Value
public class SearchRule {

	private String tag;
	private boolean isSearchingByText;
	private List<String> classes;
	private List<ElementAttribute> attributes;
}