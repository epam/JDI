package com.epam.jdi.uitests.core.settings;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */

import com.epam.jdi.uitests.core.settings.highlighting.Color;
import com.epam.jdi.uitests.core.settings.highlighting.CssColors;
import com.epam.jdi.uitests.core.settings.highlighting.IColor;

import java.util.Optional;

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public class HighlightSettings {
    private IColor bgColor = CssColors.MEDIUM_SLATE_BLUE;
    private IColor frameColor = CssColors.DIM_GRAY;
    private IColor fontColor = CssColors.CYAN;
    private int timeoutInSec = 1;

    public HighlightSettings() {
    }

public HighlightSettings(IColor bgColor, IColor frameColor, IColor fontColor, int timeoutInSec) {
    this.bgColor = bgColor;
    this.frameColor = frameColor;
    this.fontColor = fontColor;
    this.timeoutInSec = timeoutInSec;
}
    public IColor getBgColor() {
        return bgColor;
    }

    public HighlightSettings setBgColor(IColor bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public IColor getFrameColor() {
        return frameColor;
    }

    public HighlightSettings setFrameColor(IColor frameColor) {
        this.frameColor = frameColor;
        return this;
    }

    public int getTimeoutInSec() {
        return timeoutInSec;
    }

    public HighlightSettings setTimeoutInSec(int timeoutInSec) {
        this.timeoutInSec = timeoutInSec;
        return this;
    }

public IColor getFontColor() {
    return fontColor;
}

public HighlightSettings setFontColor(IColor fontColor) {
    this.fontColor = fontColor;
    return this;
}

public static IColor parseColor(String color, IColor defaultColor) {
    Optional<IColor> color1 = CssColors.tryConstructEnumColor(color);
    return color1.orElse(Optional.ofNullable(Color.tryConstructCustomShade(color )).orElse(defaultColor));
}

public static IColor parseColor(String color) {
    return parseColor(color,null );
}
}