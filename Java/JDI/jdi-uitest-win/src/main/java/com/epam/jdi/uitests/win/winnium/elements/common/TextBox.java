package com.epam.jdi.uitests.win.winnium.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.WebElementTextManager;
import org.openqa.selenium.By;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class TextBox extends Element implements ITextArea {
    private WebElementTextManager textFromWebElement = new WebElementTextManager(this);

    public TextBox() {
        super();
    }
    public TextBox(By label) {
        super(); labelLocator = label;
    }
    public By labelLocator;
    //ITextArea
    @Override
    public void inputLines(String... textLines) {
        textFromWebElement.inputLines(textLines);
    }

    @Override
    public void addNewLine(String textLine) {
        textFromWebElement.addNewLine(textLine);
    }

    @Override
    public String[] getLines() {
        return textFromWebElement.getLines();
    }

    //ISetValue
    @Override
    public void setValue(String value) {
        textFromWebElement.setValue(value);
    }

    public String label() {
        if (labelLocator == null)
            throw exception("Label locator no specified");
        Label label = new Label();
        label.getAvatar().setByLocator(labelLocator);
        label.setParent(getParent());
        return label.getText();
    }
    //ITextField
    @Override
    public void input(CharSequence text) {
        textFromWebElement.input(text);
    }

    @Override
    public void newInput(CharSequence text) {
        textFromWebElement.newInput(text);
    }

    @Override
    public void clear() {
        textFromWebElement.clear();
    }

    @Override
    public void focus() {
        textFromWebElement.focus();
    }

    //IText
    @Override
    public String getText() {
        return textFromWebElement.getText();
    }

    @Override
    public String waitText(String text) {
        return textFromWebElement.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return textFromWebElement.waitMatchText(regEx);
    }

    //IHasValue
    @Override
    public String getValue() {
        return textFromWebElement.getValue();
    }
}
