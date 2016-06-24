package com.epam.jdi.uitests.win.winium.elements.composite;
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
import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
import com.epam.jdi.uitests.win.winium.elements.BaseElement;
import com.epam.jdi.uitests.win.settings.WinSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.win.settings.WinSettings.domain;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/17/2015.
 */
public class WebPage extends BaseElement implements IPage {
    public static boolean checkAfterOpen = false;
    public String url;
    public String title;
    protected CheckPageTypes checkUrlType = CheckPageTypes.EQUAL;
    protected CheckPageTypes checkTitleType = CheckPageTypes.EQUAL;
    protected String urlTemplate;
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

    public static String getUrlFromUri(String uri) {
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    public static String getMatchFromDomain(String uri) {
        return domain.replaceAll("/*$", "").replace(".", "\\.") + "/" + uri.replaceAll("^/*", "");
    }

    public static void openUrl(String url) {
        new WebPage(url).open();
    }

    public static String getUrl() {
        return WinSettings.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
        return WinSettings.getDriver().getTitle();
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
        invoker.doJAction(format("Open page '%s' by url %s", getName(), url),
                () -> getDriver().findElements(new By.ByClassName("Notepad")));
        if (checkAfterOpen)
            checkOpened();
        currentPage = this;
        return (T) this;
    }

    public void isOpened() {
        try {
            logger.info(format("Page '%s' should be opened", getName()));
            if (getDriver().getCurrentUrl().equals(url)) return;
            open();
        } catch (Exception ex) {
            throw exception(format("Can't open page '%s'. Reason: %s", getName(), ex.getMessage()));
        }
    }

    /**
     * Refresh current page
     */
    @JDIAction
    public void refresh() {
        invoker.doJAction(format("Refresh page '%s", getName()),
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
        private Timer timer;

        public StringCheckType(Supplier<String> actual, String equals, String template, String what, Timer timer) {
            this.actual = actual;
            this.equals = equals;
            this.template = template;
            this.what = what;
            this.timer = timer;
        }

        /**
         * Check that current page url/title equals to expected url/title
         */
        @JDIAction
        public void check() {
            asserter.check(format("page %s equals to '%s'", what, equals))
                    .isTrue(timer.wait(() -> actual.get().equals(equals)));
        }

        /**
         * Check that current page url/title matches to expected url/title-matcher
         */
        @JDIAction
        public void match() {
            asserter.check(format("page %s matches to '%s'", what, template))
                    .isTrue(timer.wait(() -> actual.get().matches(template)));
        }

        /**
         * Check that current page url/title contains expected url/title-matcher
         */
        @JDIAction
        public void contains() {
            asserter.check(format("page %s contains '%s'", what, equals))
                    .isTrue(timer.wait(() -> actual.get().contains(template)));
        }
    }
}