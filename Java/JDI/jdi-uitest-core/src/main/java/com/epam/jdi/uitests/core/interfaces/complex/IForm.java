package com.epam.jdi.uitests.core.interfaces.complex;
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

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Map;

import static com.epam.jdi.uitests.core.interfaces.complex.FormFilters.MANDATORY;
import static com.epam.jdi.uitests.core.interfaces.complex.FormFilters.OPTIONAL;
import static com.epam.jdi.uitests.core.utils.common.PrintUtils.getMapFromObject;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public interface IForm<T> extends IComposite, ISetValue, IHasValue, IElement {
    /**
     * @param map Specify entity as map
     *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
     */
    @Step
    void fill(MapArray<String, String> map);

    void filter(FormFilters filter);
    @Step
    default IForm<T> onlyMandatory() {
        filter(MANDATORY);
        return this;
    }
    @Step
    default IForm<T> onlyOptional() {
        filter(OPTIONAL);
        return this;
    }
    /**
     * @param entity Specify entity
     *               Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
     */
    @Step
    default void fill(T entity) {
        fill(getMapFromObject(entity));
    }

    /**
     * @param map Specify entity as map
     *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
     */
    @Step
    default void fill(Map<String, String> map) {
        fill(MapArray.toMapArray(map));
    }

    /**
     * @param map Specify entity as mapArray
     *            Fills all elements on the form which implements SetValue interface and can be matched with fields in input entity
     */
    @Step
    List<String> verify(MapArray<String, String> map);

    /**
     * Get fields from form as specified class entity
     */
    @Step
    T getEntity();
    /**
     * @param entity Specify entity
     * Verify that form filled correctly. If not returns list of keys where verification fails
     */
    @Step
    default List<String> verify(T entity) {
        return verify(getMapFromObject(entity));
    }

    /**
     * @param map Specify entity as map
     *            Verify that form filled correctly. If not returns list of keys where verification fails
     */
    @Step
    default List<String> verify(Map<String, String> map) {
        return verify(MapArray.toMapArray(map));
    }

    /**
     * @param map Specify entity as mapArray
     *            Verify that form filled correctly. If not throws error
     */
    @Step
    void check(MapArray<String, String> map);

    /**
     * @param entity Specify entity
     *               Verify that form filled correctly. If not throws error
     */
    @Step
    default void check(T entity) {
        check(getMapFromObject(entity));
    }

    /**
     * @param map Specify entity as map
     *               Verify that form filled correctly. If not throws error
     */
    @Step
    default void check(Map<String, String> map) {
        check(MapArray.toMapArray(map));
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “submit” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element and only one IButton Element
     */
    @Step
    void submit(String text);

    /**
     * @param text       Specify text
     * @param buttonName button name for form submiting
     *                   Fill first setable field with value and click on Button “buttonName” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    void submit(String text, String buttonName);


    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “login” or ”loginButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void login(String text) {
        submit(text, "login");
    }
    @Step
    default void loginAs(String text) {
        login(text);
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “add” or ”addButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void add(String text) {
        submit(text, "add");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “publish” or ”publishButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void publish(String text) {
        submit(text, "publish");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “save” or ”saveButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void save(String text) {
        submit(text, "save");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “update” or ”updateButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void update(String text) {
        submit(text, "update");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “cancel” or ”cancelButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void cancel(String text) {
        submit(text, "cancel");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “close” or ”closeButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void close(String text) {
        submit(text, "close");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “back” or ”backButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void back(String text) {
        submit(text, "back");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “select” or ”selectButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void select(String text) {
        submit(text, "select");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “next” or ”nextButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void next(String text) {
        submit(text, "next");
    }

    /**
     * @param text Specify text
     *             Fill first setable field with value and click on Button “search” or ”searchButton” <br>
     * @apiNote To use this option Form pageObject should have at least one ISetValue element <br>
     * Allowed different buttons to send one form e.g. save/ publish / cancel / search update ...
     */
    @Step
    default void search(String text) {
        submit(text, "search");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “submit” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void submit(T entity) {
        submit(getMapFromObject(entity));
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “login” or ”loginButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void login(T entity) {
        submit(entity, "login");
    }
    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “login” or ”loginButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void loginAs(T entity) {
        login(entity);
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “login” or ”loginButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void send(T entity) {
        submit(entity, "send");
    }
    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “add” or ”addButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void add(T entity) {
        submit(entity, "add");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “publish” or ”publishButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void publish(T entity) {
        submit(entity, "publish");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “save” or ”saveButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void save(T entity) {
        submit(entity, "save");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “update” or ”updateButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void update(T entity) {
        submit(entity, "update");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “cancel” or ”cancelButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void cancel(T entity) {
        submit(entity, "cancel");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “close” or ”closeButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void close(T entity) {
        submit(entity, "close");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “back” or ”backButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void back(T entity) {
        submit(entity, "back");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “select” or ”selectButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void select(T entity) {
        submit(entity, "select");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “next” or ”nextButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void next(T entity) {
        submit(entity, "next");
    }

    /**
     * @param entity Specify entity
     *               Fill all SetValue elements and click on Button “search” or ”searchButton” <br>
     * @apiNote To use this option Form pageObject should have only one IButton Element
     */
    @Step
    default void search(T entity) {
        submit(entity, "search");
    }

    /**
     * @param buttonName Specify Button Name
     * @param entity     Specify entity
     *                   Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
     * @apiNote To use this option Form pageObject should have button names in specific format <br>
     * e.g. if you call "submit(user, "Publish") then you should have Element 'publishButton'. <br>
     * * Letters case in button name  no matters
     */
    @Step
    void submit(T entity, String buttonName);

    /**
     * @param buttonName Specify Button Name
     * @param entity     Specify entity
     *                   Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
     * @apiNote To use this option Form pageObject should have button names in specific format <br>
     * e.g. if you call "submit(user, "Publish") then you should have Element 'publishButton'. <br>
     * * Letters case in button name  no matters
     */
    @Step
    void submit(T entity, Enum buttonName);

    /**
     * @param objStrings Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
     * @apiNote To use this option Form pageObject should have button names in specific format <br>
     * e.g. if you call "submit(user, "Publish") then you should have Element 'publishButton'. <br>
     * * Letters case in button name  no matters
     */
    @Step
    void submit(MapArray<String, String> objStrings);
}