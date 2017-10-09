package com.epam.jdi.uitests.win.winnium.elements.base;

import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.elements.BaseElement;
import com.epam.jdi.uitests.win.winnium.elements.ElementsUtils;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.logger.LogLevels.DEBUG;

public class Element extends BaseElement implements IElement {
    private final Supplier<Boolean> isDisplayedSupplier =
            () -> invoker.doJActionResult("Is element displayed", () -> getWebElement().isDisplayed(), this.toString());

    public Element() {
        super();
    }

    public Element(WebElement w) {
        avatar.setWebElement(w);
    }

    @JDIAction
    public WebElement getWebElement() {
        return invoker.doJActionResult("Get web element", avatar::getElement, DEBUG, this.toString());
    }

    @Override
    public String getAttribute(String name) {
        return getWebElement().getAttribute(name);
    }

    @Override
    public void waitAttribute(String name, String value) { // Possibly, this method doesn't useful, we should check it.
        ElementsUtils.waitByCondition(() -> getWebElement().getAttribute(name), s -> s.equals(value),
                "Attribute %s hasn't set after %d seconds", name, JDISettings.timeouts.getCurrentTimeoutSec());
    }

    @Override
    public void setAttribute(String attributeName, String value) {
        throw JDISettings.exception("Not supported");
    }

    @Override
    public boolean isDisplayed() {
        return invoker.doJActionResult("Is element displayed", () -> getWebElement().isDisplayed(), this.toString());
    }

    @Override
    public boolean isHidden() {
        return invoker.doJActionResult("Is element hidden", () -> !getWebElement().isDisplayed(), this.toString());
    }

    @Override
    public void waitDisplayed() {
        ElementsUtils.waitByCondition(isDisplayedSupplier, isDisplayed -> isDisplayed,
                "Element %s hasn't displayed after %d seconds", this, JDISettings.timeouts.getCurrentTimeoutSec());
    }

    @Override
    public void waitVanished() {
        ElementsUtils.waitByCondition(isDisplayedSupplier, isDisplayed -> !isDisplayed,
                "Element %s hasn't vanished after %d seconds", this, JDISettings.timeouts.getCurrentTimeoutSec());
    }
}
