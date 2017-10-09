package com.epam.commons.pairs;
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

/**
 * Created by 12345 on 30.09.2014.
 */
public class Pair<TFirst, TSecond> {
    public TFirst key;
    public TSecond value;

    public Pair(TFirst value1, TSecond value2) {
        key = value1;
        value = value2;
    }

    public boolean equalTo(Pair<TFirst, TSecond> other) {
        return other != null && (this == other || key == other.key && value == other.value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof PairString))
            return false;
        Pair<TFirst, TSecond> pairo = (Pair<TFirst, TSecond>) o;
        return this.key.equals(pairo.key)
                && this.value.equals(pairo.value);
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }
}