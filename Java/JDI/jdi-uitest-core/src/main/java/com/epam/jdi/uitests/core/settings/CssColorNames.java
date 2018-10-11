package com.epam.jdi.uitests.core.settings;

/**
 * Created by oksana_cherniavskaia on 11.10.2018.
 */
public enum CssColorNames {

	DODGER_BLUE("DodgerBlue", "#1E90FF"), MEDIUM_SLATE_BLUE( 	"#7B68EE", "MediumSlateBlue");
	private String name;
	private String hex;

CssColorNames(String name, String hex) {
	this.name = name;
	this.hex = hex;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getHex() {
	return hex;
}

public void setHex(String hex) {
	this.hex = hex;
}
}
