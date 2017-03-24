package com.epam.jdi.uitests.web.selenium.elements.composite;
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


import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.Cookie;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/17/2015.
 */
public class WebPage extends BaseElement implements IPage {
    public static boolean checkAfterOpen = false;
    public String url;
    public String title;
    public CheckPageTypes checkUrlType = CheckPageTypes.EQUAL;
    public CheckPageTypes checkTitleType = CheckPageTypes.EQUAL;
    public String urlTemplate;
    public static WebPage currentPage;

    public WebPage() {
    }

    public WebPage(String url) {
        this.url = url;
    }

    public WebPage(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public static void openUrl(String url) {
        new WebPage(url).open();
    }

    public static String getUrl() {
        return WebSettings.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
        return WebSettings.getDriver().getTitle();
    }

    public void updatePageData(String url, String title, CheckPageTypes checkUrlType, CheckPageTypes checkTitleType, String urlTemplate) {
        if (this.url == null)
            this.url = url;
        if (this.title == null)
            this.title = title;
        this.checkUrlType = checkUrlType;
        this.checkTitleType = checkTitleType;
        this.urlTemplate = urlTemplate;
    }

    public StringCheckType url() {
        return new StringCheckType(getDriver()::getCurrentUrl, url, urlTemplate, "url", timer());
    }

    public StringCheckType title() {
        return new StringCheckType(getDriver()::getTitle, title, title, "title", timer());
    }


    /**
     * Check that page opened
     */
    public void checkOpened() {
        asserter.isTrue(verifyOpened(),
                format("Page '%s' is not opened", toString()));
    }
    public boolean verifyOpened() {
        boolean result = false;
        switch (checkUrlType) {
            case EQUAL:
                result = url().check(); break;
            case MATCH:
                result = url().match(); break;
            case CONTAINS:
                result = url().contains(); break;
        }
        if (!result)
            return false;
        switch (checkTitleType) {
            case EQUAL:
                return title().check();
            case MATCH:
                return title().match();
            case CONTAINS:
                return title().contains();
        }
        return false;
    }

    /**
     * Opens url specified for page
     */
    public <T extends IPage> T open() {
        invoker.doJAction(format("Open page '%s' by url %s", getName(), url),
                () -> getDriver().navigate().to(url));
        if (checkAfterOpen)
            checkOpened();
        currentPage = this;
        return (T) this;
    }
    public void shouldBeOpened() {
        isOpened();
    }
    /**
     * @deprecated  Better use more obvious {@link #shouldBeOpened()}
     */
    @Deprecated
    public void isOpened() {
        try {
            logger.info(format("Page '%s' should be opened", getName()));
            if (verifyOpened()) return;
            open();
            checkOpened();
        } catch (Exception ex) {
            throw exception(format("Can't open page '%s'. Reason: %s", getName(), ex.getMessage()));
        }
    }

    /**
     * Refresh current page
     */
    @Step
    public void refresh() {
        invoker.doJAction(format("Refresh page '%s", getName()),
                () -> getDriver().navigate().refresh());
    }
    /**
     * Reload current page
     */
    @Step
    public void reload() {
        invoker.doJAction(format("Reload page '%s", getName()),
                () -> getDriver().navigate().refresh());
    }

    /**
     * Go back to previous page
     */
    @Step
    public void back() {
        invoker.doJAction("Go back to previous page",
                () -> getDriver().navigate().back());
    }


    /**
     * Go forward to next page
     */
    @Step
    public void forward() {
        invoker.doJAction("Go forward to next page",
                () -> getDriver().navigate().forward());
    }

    /**
     * @param cookie Specify cookie
     *               Add cookie in browser
     */
    @Step
    public void addCookie(Cookie cookie) {
        invoker.doJAction("Add cookie",
                () -> getDriver().manage().addCookie(cookie));
    }

    /**
     * Clear browsers cache
     */
    @Step
    public void clearCache() {
        invoker.doJAction("Delete all cookies",
                () -> getDriver().manage().deleteAllCookies());
    }

    @Override
    public String toString() {
        return getName() + "(" + url + ")";
    }

    public class StringCheckType {
        private Supplier<String> actual;
        private String equals;
        private String template;
        private String what;
        private Timer timer;

        StringCheckType(Supplier<String> actual, String equals, String template, String what, Timer timer) {
            this.actual = actual;
            this.equals = equals;
            this.template = template;
            this.what = what;
            this.timer = timer;
        }

        /**
         * Check that current page url/title equals to expected url/title
         */
        @Step
        public boolean check() {
            logger.info(format("Check that page %s equals to '%s'", what, equals));
            return equals == null
                || equals.equals("")
                || actual.get().equals(equals);
        }

        /**
         * Check that current page url/title matches to expected url/title-matcher
         */
        @Step
        public boolean match() {
            logger.info(format("Check that page %s matches to '%s'", what, template));
            return template == null
                || template.equals("")
                || actual.get().matches(template);
        }

        /**
         * Check that current page url/title contains expected url/title-matcher
         */
        @Step
        public boolean contains() {
            logger.info(format("Check that page %s contains to '%s'", what, template));
            return template == null
                    || template.equals("")
                    || actual.get().contains(template);
        }
    }
}