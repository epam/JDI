package com.epam.jdi.uitests.gui.sikuli.elements.apiInteract;

import com.epam.commons.Timer;
import com.epam.commons.pairs.Pairs;
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.gui.GuiSettings;
import com.epam.jdi.uitests.gui.sikuli.driver.DriverTypes;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;
import com.epam.jdi.uitests.gui.sikuli.elements.enums.ContextType;
import com.epam.jdi.uitests.gui.sikuli.elements.enums.OffsetUnits;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
public class GetElementModule implements IAvatar {

    private static final String FAILED_TO_FIND_ELEMENT_MESSAGE = "Can't find Element '%s' during %s seconds";
    private static final String FIND_TO_MUCH_ELEMENTS_MESSAGE = "Find %s elements instead of one for Element '%s' during %s seconds";
    public int x;
    public int y;
    public int w;
    public int h;
    public int xOffset;
    public int yOffset;
    public OffsetUnits offsetUnits;
    public Rectangle rectangle;
    public double similarity;
    public Pattern pattern;
    public Region rootElement;
    String driverName = "";
    private IBaseElement element;
    public Pairs<ContextType, Pattern> context = new Pairs<>();

    public GetElementModule(IBaseElement element) {
        this.element = element;
        driverName = DriverTypes.SIKULI.name;
    }

    public GetElementModule(String filePath, IBaseElement element) {
        this(element);
        if (filePath == null)
            return;
        this.pattern = new Pattern(GuiSettings.imageRoot + filePath);
    }

    public GetElementModule(Pattern pattern, IBaseElement element) {
        this(element);
        if (pattern == null)
            return;
        this.pattern = new Pattern(pattern);
    }

    public GetElementModule(String filePath, Region rootElement, IBaseElement element) {
        this(element);
        this.pattern = new Pattern(filePath);
        this.rootElement = rootElement;
    };

    public String getDriverName() {
        return driverName;
    }
    public Element getElement() {
        logger.debug("Get Element: " + element);
        Element element = timer().getResultByCondition((Supplier<Element>) this.getElementsAction(), el -> el != null);
        logger.debug("One Element found");
        return element;
    }

    private List<Element> getElementsAction() {
        List<Element> result = this.searchElements();
        timeouts.dropTimeouts();
        if (result == null)
            throw exception("Can't get Web Elements");
        return result;
    }
    private List<Element> searchElements() {
        /*if (this.context == null || this.context.isEmpty())
            return getDriver().findElements(byLocator);
        SearchContext context = (rootElement != null)
                ? rootElement
                : getSearchContext(correctXPaths(this.context));
        return context.findElements(correctXPaths(byLocator));*/

        return  null;
    }
    public double getSimilarity() { return similarity; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getW() { return w; }
    public int getH() { return h; }
    public Rectangle getRectangle() { return rectangle; }

    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    public void setRectangle(Rectangle rectangle) { this.rectangle = rectangle; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setW(int w) { this.w = w; }
    public void setH(int h) { this.h = h; }


    public Timer timer() {
        return new Timer(timeouts.currentTimeoutSec * 1000);
    }
}
