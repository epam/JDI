package com.epam.jdi.uitests.win.winium.elements.common;
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


import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Text Field control implementation
 *
 * @author Alexeenko Yan
 * @author Shubin Konstantin
 * @author Zharov Alexandr
 */
public class TextArea extends TextField implements ITextArea {
    public TextArea() {
    }

    public TextArea(By byLocator) {
        super(byLocator);
    }

    public TextArea(WebElement webElement) {
        super(webElement);
    }

    public final void inputLines(String... textLines) {
        actions.inputLines(this::clearAction, this::inputAction, textLines);
    }

    public final void addNewLine(String textLine) {
        actions.addNewLine(textLine, this::inputAction);
    }

    public final String[] getLines() {
        return actions.getLines(this::getTextAction);
    }
}