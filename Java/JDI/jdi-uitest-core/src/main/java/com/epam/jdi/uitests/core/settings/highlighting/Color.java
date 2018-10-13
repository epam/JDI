package com.epam.jdi.uitests.core.settings.highlighting;

import com.epam.jdi.uitests.core.settings.JDISettings;

/**
 * Created by oksana_cherniavskaia on 13.10.2018.
 */
public class Color implements IColor {

public static final String SHADE = "SHADE";
private long color;

public Color(long color) {
	this.color = color;
}
public Color(int r, int g, int b) {
	this.color = r&0xFF0000|g&0x00ff00|b&0x0000ff;
}

public static IColor tryConstructCustomShade(String color) {
	Long res=null;
	try{
	res = Long.decode(color);
	}catch (NumberFormatException e){
		JDISettings.logger.debug("Invalid color string. Unable to parse hex value: %s", color);
	}
	return res==null? null:new Color(res);
}

@Override
public String getName() {
	return SHADE;
}



@Override
public long getRgb() {
	return color;
}
}
