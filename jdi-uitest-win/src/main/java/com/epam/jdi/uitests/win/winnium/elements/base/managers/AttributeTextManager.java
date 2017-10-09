package com.epam.jdi.uitests.win.winnium.elements.base.managers;

import com.epam.jdi.uitests.win.winnium.elements.base.Element;

import static com.epam.commons.Timer.getByCondition;
import static java.lang.String.format;

public class AttributeTextManager {
    private Element element;

    public AttributeTextManager(Element element) {
        this.element = element;
    }

    private String getTextMethod() {
        return element.getWebElement().getAttribute("Name");
    }

    private String invokeGetTextMethod() {
        return element.getInvoker().doJActionResult("Get text", this::getTextMethod, element.toString());
    }

    public String getText() {
        return invokeGetTextMethod();
    }

    public String waitText(String text) {
        return element.getInvoker().doJActionResult(format("Wait text contains '%s'", text),
                () -> getByCondition(this::getTextMethod, t -> t.contains(text)), element.toString());
    }

    public String waitMatchText(String regEx) {
        return element.getInvoker().doJActionResult(format("Wait text match regex '%s'", regEx),
                () -> getByCondition(this::getTextMethod, t -> t.matches(regEx)), element.toString());
    }

    public String getValue() {
        return invokeGetTextMethod();
    }
}
