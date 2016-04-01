package com.epam.jdi.uitests.gui.sikuli.elements.actions;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.gui.sikuli.elements.BaseElement;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Natalia_Grebenshchik on 1/14/2016.
 */
public class ElementsActions {
    private BaseElement element;

    public ActionInvoker invoker() {
        return element.invoker;
    }

    public ElementsActions(BaseElement element) {
        this.element = element;
    }

    // Click Action
    public void click(JAction clickAction) {
        invoker().doJAction("Click on Element", clickAction);
    }

    //Input Actions
    public void input(CharSequence text, Consumer<CharSequence> inputAction) {
        invoker().doJAction("Input text '" + text + "' in text field",
                () -> inputAction.accept(text));
    }

    // Element Actions
    public boolean isDisplayed(Supplier<Boolean> isDisplayed) {
        return invoker().doJActionResult("Is element displayed", isDisplayed);
    }

    public void waitVanished(Supplier<Boolean> isVanished) {
        invoker().doJActionResult("Wait element vanished", isVanished);
    }
}
