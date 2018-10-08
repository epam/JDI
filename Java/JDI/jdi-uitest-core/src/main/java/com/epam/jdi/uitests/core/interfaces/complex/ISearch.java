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
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public interface ISearch extends ITextField, IComposite {
    /**
     * @param text        Specify Text to search
     * @param selectValue Specify value to choose from suggested search result
     *                    Input text in search and then select value from suggestions
     */
    @Step
    void chooseSuggestion(String text, String selectValue);

    /**
     * @param text        Specify Text to search
     * @param selectIndex Specify index to choose from suggested search result
     *                    Input text in search and then select suggestions by index
     */
    @Step
    void chooseSuggestion(String text, int selectIndex);

    /**
     * @param text Specify Text to search
     *             Input text in search field and press search button
     */
    @Step
    void find(String text);

    /**
     * @param text Specify Text to search
     * @return Select all suggestions for text
     */
    @Step
    List<String> getSuggesions(String text);
}