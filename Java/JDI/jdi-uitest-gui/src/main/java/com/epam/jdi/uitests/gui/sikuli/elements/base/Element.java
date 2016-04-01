package com.epam.jdi.uitests.gui.sikuli.elements.base;

import com.epam.commons.TryCatchUtil;
import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.gui.sikuli.elements.BaseElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.*;

import static com.epam.jdi.uitests.core.logger.LogLevels.DEBUG;
import static java.lang.String.format;

/**
 * Created by Natalia_Grebenshchik on 1/14/2016.
 */
public class Element extends BaseElement implements IElement {

    public Element() {
        super();
    }

    public Element(String filePath) {
    }

    public Element(Pattern pattern) {
        super(pattern);
    }

    @JDIAction
    public Element getElement() {
        return invoker.doJActionResult("Get gui element",
                () -> this != null ? this : avatar.getElement(), DEBUG);
    }

    public void rightClick() {
        invoker.doJAction("Right click on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().rightClick(this.getPattern()));
        });
    }

    public void doubleClick() {
        invoker.doJAction("Double click on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().doubleClick(this.getPattern()));
        });
    }

    protected boolean isDisplayedAction() {
        return this.getParentRegion().exists(this.getPattern()) != null;
    }

    @Override
    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }

    @Override
    public boolean isHidden() {
        return actions.isDisplayed(() -> !isDisplayedAction());
    }

    @Override
    public void waitVanished() {
        if (timer().wait(() -> isDisplayed()))
            throw new RuntimeException(String.format("Element '%s' not vanished after timeout", this.getName()));
    }

    @Override
    public void waitDisplayed() {
        if (timer().wait(() -> isHidden()))
            throw new RuntimeException(String.format("Element '%s' not displayed after timeout", this.getName()));
    }

    public void highlight(HighlightSettings highlightSettings) {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().find(this.getPattern())
                .highlight(highlightSettings.getTimeoutInSec(), highlightSettings.getFrameColor()));
    }

    public void highlight() {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().find(this.getPattern()).highlight());
    }

    public void clickWithKeys(int... keys) {
        invoker.doJAction("Ctrl click on Element", () -> {
            Robot robot = null;
            try {
                robot = new Robot();
                for (int key : keys)
                    robot.keyPress(key);
                this.getParentRegion().click(this.getPattern());

            } catch (Exception | Error e) {
            } finally {
                for (int key : keys)
                    robot.keyRelease(key);
            }
        });

    }

    public void mouseOver() {
        invoker.doJAction("Move mouse over Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().mouseMove(this.getPattern()));
        });
    }

    public void focus() {
        invoker.doJAction("Focuce on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().mouseMove(this.getPattern()));
        });
    }

    public void selectArea(int x1, int y1, int x2, int y2) {
        invoker.doJAction(format("Select area: from %d,%d;to %d,%d", x1, y1, x2, y2), () -> {

            TryCatchUtil.tryGetResult(() -> new Screen().dragDrop(new Region(x1, y2, 1, 1), new Region(x2, y2, 1, 1)));

        });
    }

    public void selectArea(Element e1, Element e2) {
        invoker.doJAction(format("Select are: from element '%s' ti '%s", e1.getName(), e2.getName()),
                () -> TryCatchUtil.tryGetResult(() -> this.getParentRegion().dragDrop(e1.getPattern(), e2.getPattern())));
    }

    public void dragAndDropBy(Element e) {
        invoker.doJAction(format("Drag and drop Element '%s' to element '%s'", this.getName(), e.getName()),
                () -> TryCatchUtil.tryGetResult(() -> this.getParentRegion().dragDrop(this.getPattern(), e.getPattern())));
    }

    //To  Move to diff place
    public String getAttribute(String name) {
        return null;
    }

    public void waitAttribute(String name, String value) {

    }

    public void setAttribute(String attributeName, String value) {

    }
}
