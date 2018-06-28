package com.epam.jdi.uitests.core.interfaces.base;
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

import com.codeborne.selenide.Condition;
import com.epam.jdi.uitests.core.annotations.functions.Functions;

import java.lang.reflect.Field;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public interface IBaseElement extends IHasParent {
    /**
     * @return Get Element’s name
     */
    String getName();
    void setName(Field field);
    void setTypeName(String typeName);
    String printContext();
    void setFunction(Functions function);
    IAvatar getAvatar();

    IBaseElement should(Condition... condition);
    IBaseElement shouldHave(Condition... condition);
    IBaseElement shouldBe(Condition... condition);
    IBaseElement shouldNot(Condition... condition);
    IBaseElement shouldNotHave(Condition... condition);
    IBaseElement shouldNotBe(Condition... condition);
}