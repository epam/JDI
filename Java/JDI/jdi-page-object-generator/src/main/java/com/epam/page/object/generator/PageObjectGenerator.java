package com.epam.page.object.generator;

public class PageObjectGenerator {

	private String jsonPath;
	private String url;
	private String outputDir;

	public PageObjectGenerator(String jsonPath, String url, String outputDir) {
		this.jsonPath = jsonPath;
		this.url = url;
		this.outputDir = outputDir;
	}
}