package com.epam.page.object.generator.model;

public class AnnotationDescription {

	private String name;
	private String args;

	public AnnotationDescription(String name, String args) {
		this.name = name;
		this.args = args;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

}