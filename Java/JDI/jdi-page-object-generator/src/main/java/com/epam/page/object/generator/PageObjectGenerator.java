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

	public String getJsonPath() {
		return jsonPath;
	}

	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

}