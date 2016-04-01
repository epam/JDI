package com.epam.jdi.uitests.mobile.appium.elements.composite;
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


import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
import com.epam.jdi.uitests.mobile.WebSettings;
import com.epam.jdi.uitests.mobile.appium.elements.BaseElement;
import com.epam.web.matcher.testng.Check;
import org.openqa.selenium.Cookie;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.mobile.WebSettings.domain;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/17/2015.
 */
public class AppPage extends BaseElement implements IPage {
    public static boolean checkAfterOpen = false;
    public String url;
    public String title;
    protected CheckPageTypes checkUrlType = CheckPageTypes.EQUAL;
    protected CheckPageTypes checkTitleType = CheckPageTypes.EQUAL;
    protected String urlTemplate;

    public AppPage() {
    }

    public AppPage(String url) {
        this.url = url;
    }

    public AppPage(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public static String getUrlFromUri(String uri) {
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    public static String getMatchFromDomain(String uri) {
        return domain.replaceAll("/*$", "").replace(".", "\\.") + "/" + uri.replaceAll("^/*", "");
    }

    public static void openUrl(String url) {
        new AppPage(url).open();
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
        return new StringCheckType(getDriver()::getCurrentUrl, url, urlTemplate, "url");
    }

    public StringCheckType title() {
        return new StringCheckType(getDriver()::getTitle, title, title, "title");
    }

    public void checkOpened() {
        switch (checkUrlType) {
            case EQUAL:
                url().check();
                break;
            case MATCH:
                url().match();
                break;
            case CONTAIN:
                url().contains();
                break;
        }
        switch (checkTitleType) {
            case EQUAL:
                title().check();
                break;
            case MATCH:
                title().match();
                break;
            case CONTAIN:
                title().contains();
                break;
        }
    }

    public <T extends IPage> T open() {
        invoker.doJAction(format("Open page %s by url %s", getName(), url),
                () -> getDriver().navigate().to(url));
        if (checkAfterOpen)
            checkOpened();
        return (T) this;
    }

    public void isOpened() {
        try {
            logger.info("Page %s is opened", getName());
            if (getDriver().getCurrentUrl().equals(url)) return;
            open();
        } catch (Exception ex) {
            throw exception(format("Can't open page %s. Exception: %s", getName(), ex.getMessage()));
        }

    }

    /**
     * Refresh current page
     */
    @JDIAction
    public void refresh() {
        invoker.doJAction("Refresh page " + getName(),
                () -> getDriver().navigate().refresh());
    }

    /**
     * Go back to previous page
     */
    @JDIAction
    public void back() {
        invoker.doJAction("Go back to previous page",
                () -> getDriver().navigate().back());
    }


    /**
     * Go forward to next page
     */
    @JDIAction
    public void forward() {
        invoker.doJAction("Go forward to next page",
                () -> getDriver().navigate().forward());
    }

    /**
     * @param cookie Specify cookie
     *               Add cookie in browser
     */
    @JDIAction
    public void addCookie(Cookie cookie) {
        invoker.doJAction("Go forward to next page",
                () -> getDriver().manage().addCookie(cookie));
    }

    /**
     * Clear browsers cache
     */
    @JDIAction
    public void clearCache() {
        invoker.doJAction("Go forward to next page",
                () -> getDriver().manage().deleteAllCookies());
    }

    public class StringCheckType {
        private Supplier<String> actual;
        private String equals;
        private String template;
        private String what;

        public StringCheckType(Supplier<String> actual, String equals, String template, String what) {
            this.actual = actual;
            this.equals = equals;
            this.template = template;
            this.what = what;
        }

        /**
         * Check that current page url/title equals to expected url/title
         */
        @JDIAction
        public void check() {
            new Check(format("Page %s equals to '%s'", what, equals)).areEquals(actual, equals);
        }

        /**
         * Check that current page url/title matches to expected url/title-matcher
         */
        @JDIAction
        public void match() {
            new Check(format("Page %s matches to '%s'", what, template)).matches(actual, template);
        }

        /**
         * Check that current page url/title contains expected url/title-matcher
         */
        @JDIAction
        public void contains() {
            new Check(format("Page %s contains '%s'", what, template)).contains(actual, template);
        }
    }
}