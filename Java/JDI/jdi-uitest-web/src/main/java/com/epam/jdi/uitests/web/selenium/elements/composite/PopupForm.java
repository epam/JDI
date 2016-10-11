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


import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.IPopup;

import static com.epam.jdi.uitests.core.annotations.functions.Functions.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class PopupForm<T> extends Form<T> implements IPopup {
    /**
     * @param objStrings Fill all SetValue elements and click on Button specified button e.g. "Publish" or "Save" <br>
     * @apiNote To use this option Form pageObject should have button names in specific format <br>
     * e.g. if you call "submit(user, "Publish") then you should have Element 'publishButton'. <br>
     * * Letters case in button name  no matters
     */
    @Override
    public void submit(MapArray<String, String> objStrings) {
        fill(objStrings);
        ok();
    }

    /**
     * Click on Button marked with annotation @OkButton or named "okButton"
     */
    public void ok() {
        getElementClass.getButton(OK_BUTTON).click();
    }

    /**
     * Click on Button marked with annotation @CancelButton or named "cancelButton"
     */
    public void cancel() {
        getElementClass.getButton(CANCEL_BUTTON).click();
    }

    /**
     * Click on Button marked with annotation @CloseButton or named "closeButton"
     */
    public void close() {
        getElementClass.getButton(CLOSE_BUTTON).click();
    }

    protected String getTextAction() {
        return getWebElement().getText();
    }

    /**
     * @return Get Element’s text
     */
    public final String getText() {
        return invoker.doJActionResult("Get text", this::getTextAction);
    }

    /**
     * @param text Specify expected text
     * @return Wait while Element’s text contains expected text. Returns Element’s text
     */
    public final String waitText(String text) {
        return invoker.doJActionResult(format("Wait text contains '%s'", text),
                () -> timer().getResultByCondition(this::getTextAction, t -> t.contains(text)));
    }

    /**
     * @param regEx Specify expected regular expression Text
     * @return Wait while Element’s text matches regEx. Returns Element’s text
     */
    public final String waitMatchText(String regEx) {
        return invoker.doJActionResult(format("Wait text match regex '%s'", regEx),
                () -> timer().getResultByCondition(this::getTextAction, t -> t.matches(regEx)));
    }
}