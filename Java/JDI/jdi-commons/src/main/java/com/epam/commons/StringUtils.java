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

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by roman.i on 19.11.2014.
 */
public final class StringUtils {
    public static final String LINE_BREAK = System.getProperty("line.separator");

    public static boolean namesEqual(String name1, String name2) {
        return name1.toLowerCase().replace(" ", "").equals(name2.toLowerCase().replace(" ", ""));
    }
    public static String correctPath(String path) {
        return path.replace("\\", File.separator);
    }

    private StringUtils() {
    }

/**
 *
 * @param input
 * @param delimeter
 * @return
 */
public static String toUpperCamelCase(String input, char delimeter) {
  return Arrays.stream(input.split(String.format("%s+",delimeter)))
      .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
      .collect(Collectors.joining());
}

/**
 *
 * @param input
 * @param delimeter
 * @return
 */
public static String toLowerCamelCase(String input, char delimeter) {
    String value = toUpperCamelCase(input, delimeter);
    return value.substring(0,1).toLowerCase().concat(value.substring(1));
}
}