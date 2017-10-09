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

import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public interface IPopup extends IText, IComposite {
    /**
     * Click on Button marked with annotation @OkButton or named "okButton"
     */
    @Step
    void ok();

    /**
     * Click on Button marked with annotation @CancelButton or named "cancelButton"
     */
    @Step
    void cancel();

    /**
     * Click on Button marked with annotation @CloseButton or named "closeButton"
     */
    @Step
    void close();
}