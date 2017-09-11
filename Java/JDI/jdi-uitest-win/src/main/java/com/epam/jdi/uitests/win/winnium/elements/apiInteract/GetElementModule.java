package com.epam.jdi.uitests.win.winnium.elements.apiInteract;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

public class GetElementModule implements IAvatar {
    private static final String FAILED_TO_FIND_ELEMENT_MESSAGE = "Can't find Element '%s' during %s seconds";
    private static final String FIND_TO_MUCH_ELEMENTS_MESSAGE = "Find %s elements instead of one for Element '%s' during %s seconds";

    private String driverName = "";
    private IBaseElement element;
    private By byLocator;
    private WebElement webElement;


    public GetElementModule(IBaseElement element) {
        this.element = element;
        driverName = JDISettings.driverFactory.currentDriverName();
    }

    public GetElementModule(By byLocator, IBaseElement element) {
        this(element);
        this.byLocator = byLocator;
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public Timer timer() {
        return new Timer(JDISettings.timeouts.getCurrentTimeoutSec() * 1000);
    }

    public WebElement getElement() {
        JDISettings.logger.debug("Get Web Element: " + element);

        if (webElement == null) {
            webElement = timer().getResultByCondition(this::getElementAction, el -> el != null);
            JDISettings.logger.debug("One Element found");
        }

        return webElement;
    }

    public List<WebElement> getElements() {
        logger.debug("Get Web elements: " + element);
        List<WebElement> elements = getElementsAction();
        logger.debug("Found %s elements", elements.size());
        return elements;
    }

    private List<WebElement> getElementsAction() {
        List<WebElement> result = timer().getResult(this::searchElements);

        if (result == null)
            throw JDISettings.exception("Can't get Web Elements");

        return result;
    }

    private List<WebElement> searchElements() {
        SearchContext searchContext = getSearchContext(element.getParent());

        return searchContext.findElements(byLocator);
    }

    private SearchContext getSearchContext(Object element) {
        if (element == null || !(element instanceof BaseElement))
            return getDriver();

        BaseElement bElement = (BaseElement) element;
        By locator = bElement.getAvatar().getByLocator();

        SearchContext searchContext = getSearchContext(bElement.getParent());

        return locator != null
                ? searchContext.findElement(locator)
                : searchContext;
    }

    public WebDriver getDriver() {
        return (WebDriver) JDISettings.driverFactory.getDriver(driverName);
    }

    private WebElement getElementAction() {
        int timeout = JDISettings.timeouts.getCurrentTimeoutSec();
        List<WebElement> result = getElementsAction();
        switch (result.size()) {
            case 0:
                throw JDISettings.exception(FAILED_TO_FIND_ELEMENT_MESSAGE, element, timeout);
            case 1:
                return result.get(0);
            default:
                throw JDISettings.exception(FIND_TO_MUCH_ELEMENTS_MESSAGE, result.size(), element, timeout);
        }
    }

    @Override
    public String toString() {
        if (JDISettings.shortLogMessagesFormat)
            return printFullLocator();

        String str = String.format("Locator: '%s'", byLocator);

        if (element.getParent() != null && (element.getParent() instanceof IBaseElement))
            str = str + String.format(", Context: '%s'", element.printContext());

        return str;
    }

    private String printFullLocator() {
        if (byLocator == null)
            return "No Locators";
        return element.printContext() + "; " + printShortBy(byLocator);
    }

    private String printShortBy(By by) {
        return String.format("%s='%s'", getByStr(by), getByLocator(by));
    }

    private String getByLocator(By by) {
        String byAsString = by.toString();
        int index = byAsString.indexOf(": ") + 2;
        return byAsString.substring(index);
    }

    private String getByStr(By by) {
        Matcher m = Pattern.compile("By\\.(?<locator>.*):.*").matcher(by.toString());
        if (m.find())
            return m.group("locator");
        throw new RuntimeException("Can't get By name for: " + by);
    }

    public boolean hasLocator() {
        return byLocator != null;
    }

    public boolean hasWebElement() {
        return webElement != null;
    }


    public By getByLocator() {
        return byLocator;
    }

    public void setByLocator(By byLocator) {
        this.byLocator = byLocator;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public IAvatar copy() {
        GetElementModule clone = new GetElementModule(byLocator, element);
        clone.driverName = driverName;
        clone.element = element;
        clone.webElement = webElement;

        return clone;
    }
}
