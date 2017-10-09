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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;

/**
 * Created by 12345 on 30.09.2014.
 */
public class Pairs<TValue1, TValue2> extends ArrayList<Pair<TValue1, TValue2>> {
    public Pairs() {
    }

    public Pairs(List<Pair<TValue1, TValue2>> pairs) {
        if (pairs == null) return;
        pairs.forEach(this::add);
    }

    public Pairs(TValue1 value1, TValue2 value2, Collection<Pair<TValue1, TValue2>> pairs) {
        if (pairs != null)
            pairs.forEach(this::add);
        add(value1, value2);
    }

    public static <T, TValue1, TValue2> Pairs<TValue1, TValue2> toPairs(Iterable<T> list,
                Function<T, TValue1> selectorValue1, Function<T, TValue2> selectorValue2) {
        Pairs<TValue1, TValue2> pairs = new Pairs<>();
        for (T element : list)
            pairs.add(selectorValue1.apply(element), selectorValue2.apply(element));
        return pairs;
    }

    public Pairs<TValue1, TValue2> add(TValue1 value1, TValue2 value2) {
        this.add(new Pair(value1, value2));
        return this;
    }

    public Pairs<TValue1, TValue2> add(Pairs<TValue1, TValue2> pairs) {
        pairs.foreach(this::add);
        return this;
    }

    public void addNew(TValue1 value1, TValue2 value2) {
        clear();
        add(new Pair(value1, value2));
    }

    public void foreach(Consumer<Pair<TValue1, TValue2>> action) {
        this.forEach(action::accept);
    }

    public Pairs<TValue1, TValue2> subList(int from) {
        return new Pairs<>(subList(from, size()));
    }

    @Override
    public String toString() {
        return print(select(this, pair -> pair.key + ":" + pair.value));
    }

    @Override
    public Pairs<TValue1, TValue2> clone() {
        return new Pairs<>(this);
    }

    public Pairs<TValue1, TValue2> copy() {
        return clone();
    }
}