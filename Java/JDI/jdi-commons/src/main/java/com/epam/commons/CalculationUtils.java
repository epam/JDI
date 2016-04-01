package com.epam.commons;
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

import java.util.List;

/**
 * Created by Roman_Iovlev on 7/26/2015.
 */
public final class CalculationUtils {
    private CalculationUtils() { }
    public static double average(List<Long> collection) {
        if (collection == null || collection.size() == 0)
            return 0;
        if (collection.size() == 1)
            return collection.get(0);
        double average = collection.get(0);
        for (int i = 1; i < collection.size(); i++)
            average = i * (average + collection.get(i).doubleValue() / i) / (i + 1);
        return average;
    }

}