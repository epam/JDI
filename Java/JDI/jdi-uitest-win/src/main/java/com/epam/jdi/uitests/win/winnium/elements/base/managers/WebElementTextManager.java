package com.epam.jdi.uitests.win.winnium.elements.base.managers;

import com.epam.jdi.uitests.win.robot.JRobot;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import org.openqa.selenium.WebElement;

import static com.epam.commons.Timer.getByCondition;
import static java.lang.String.format;

public class WebElementTextManager {
    private Element element;

    public WebElementTextManager(Element element) {
        this.element = element;
    }

    private String getTextAction() {
        return element.getWebElement().getText();
    }

    private void inputLinesAction(String[] textLines) {
        if (textLines == null || textLines.length == 0)
            return;

        WebElement webElement = element.getWebElement();

        webElement.click();
        JRobot.pasteText(textLines[0]);
        for (int i = 1; i < textLines.length; i++) {
            webElement.submit();
            JRobot.pasteText(textLines[i]);
        }
    }

    private void addNewLineAction(String textLine) {
        element.getWebElement().submit();
        JRobot.pasteText(textLine);
    }

    public void inputLines(String... textLines) {
        element.getInvoker().doJAction("Input several lines of text", () -> inputLinesAction(textLines),
                element.toString());
    }

    public void addNewLine(String textLine) {
        element.getInvoker().doJAction("Add text from new line", () -> addNewLineAction(textLine), element.toString());
    }

    public String[] getLines() {
        return element.getInvoker().doJActionResult("Get text as lines",
                () -> element.getWebElement().getText().split(System.getProperty("line.separator")),
                element.toString());
    }

    public void input(CharSequence text) {
        element.getInvoker().doJAction("Input text '" + text + "' in text field",
                () -> inputLinesAction(text.toString().split(System.getProperty("line.separator"))),
                element.toString());
    }

    public void newInput(CharSequence text) {
        element.getInvoker().doJAction("New text '" + text + "' in text field",
                () -> element.getWebElement().sendKeys(text), element.toString());
    }

    public void clear() {
        element.getInvoker().doJAction("Clear text field", () -> element.getWebElement().clear(), element.toString());
    }

    public void focus() {
        element.getInvoker().doJAction("Focus on text field", () -> element.getWebElement().click(),
                element.toString());
    }

    public String getText() {
        return element.getInvoker().doJActionResult("Get text", this::getTextAction, element.toString());
    }

    public String waitText(String text) {
        return element.getInvoker().doJActionResult(format("Wait text contains '%s'", text),
                () -> getByCondition(this::getTextAction, t -> t.contains(text)), element.toString());
    }

    public String waitMatchText(String regEx) {
        return element.getInvoker().doJActionResult(format("Wait text match regex '%s'", regEx),
                () -> getByCondition(this::getTextAction, t -> t.matches(regEx)), element.toString());
    }

    public String getValue() {
        return getText();
    }

    public void setValue(String value) {
        newInput(value);
    }
}
