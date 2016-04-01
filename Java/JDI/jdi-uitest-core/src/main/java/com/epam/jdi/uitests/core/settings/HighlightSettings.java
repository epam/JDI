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

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public class HighlightSettings {
    private String bgColor = "yellow";
    private String frameColor = "red";
    private int timeoutInSec = 2;

    public HighlightSettings() {
    }

    public HighlightSettings(String bgColor, String frameColor, int timeoutInSec) {
        this.bgColor = bgColor;
        this.frameColor = frameColor;
        this.timeoutInSec = timeoutInSec;
    }

    public String getBgColor() {
        return bgColor;
    }

    public HighlightSettings setBgColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public String getFrameColor() {
        return frameColor;
    }

    public HighlightSettings setFrameColor(String frameColor) {
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
}