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


import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.complex.IPopup;
import com.epam.jdi.uitests.mobile.appium.elements.common.Text;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class Popup extends Text implements IPopup {

    @Override
    protected String getTextAction() {
        return getWebElement().getText();
    }

    /**
     * Click on Button marked with annotation @OkButton or named "okButton"
     */
    public void ok() {
        getElementClass.getButton(Functions.OK_BUTTON).click();
    }

    /**
     * Click on Button marked with annotation @CancelButton or named "cancelButton"
     */
    public void cancel() {
        getElementClass.getButton(Functions.CANCEL_BUTTON).click();
    }

    /**
     * Click on Button marked with annotation @CloseButton or named "closeButton"
     */
    public void close() {
        getElementClass.getButton(Functions.CLOSE_BUTTON).click();
    }

}