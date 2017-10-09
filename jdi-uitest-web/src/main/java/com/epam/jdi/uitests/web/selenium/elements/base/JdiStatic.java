package com.epam.jdi.uitests.web.selenium.elements.base;

import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.commons.LinqUtils.first;

/**
 * Created by Roman_Iovlev on 2/21/2017.
 */
public class JdiStatic {
    public static J $(WebElement webElement) {
        return new J(webElement);
    }

    public static J $(String cssSelector) {
        return new J(By.cssSelector(cssSelector));
    }

    public static J $(By locator) {
        return new J(locator);
    }

    public static List<J> $$(Collection<? extends WebElement> elements) {
        List<J> r = new ArrayList<>();
        for(WebElement el : elements)
            r.add($(el));
        return r;
    }

    public static List<J> $$(String cssSelector) {
        return $$(By.cssSelector(cssSelector));
    }

    public static List<J> $$(By locator) {
        return $$(WebSettings.getDriver().findElements(locator));
    }

    public static J find(List<J> jList, String value) {
        return first(jList, e -> e.getText().equals(value));
    }
}
