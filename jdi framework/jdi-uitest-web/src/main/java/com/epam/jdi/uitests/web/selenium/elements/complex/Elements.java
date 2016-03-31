package com.epam.jdi.uitests.web.selenium.elements.complex;
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


import com.epam.jdi.uitests.web.selenium.elements.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.epam.jdi.commons.EnumUtils.getEnumValue;
import static com.epam.jdi.commons.LinqUtils.first;
import static com.epam.jdi.commons.LinqUtils.select;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;


/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class Elements<TType extends Element> extends BaseElement implements List<TType> {
    private Class<TType> classType;

    public Elements() {
    }

    public Elements(Class<TType> classType) {
        this.classType = classType;
    }
    public Elements(By byLocator) {
        super(byLocator);
    }
    public Elements(By byLocator, Class<TType> classType) {
        super(byLocator);
        this.classType = classType;
    }

    protected boolean getElementByNameAction(WebElement element, String name) {
        return element.getText().equals(name);
    }

    private int count = -1;
    private List<TType> elements;

    public List<TType> getAll() {
        //if (elements == null || elements.isEmpty()) {
            List<WebElement> els = getAvatar().searchAll().getElements();
            elements = select(els, el -> {
                try {
                    return classType.getDeclaredConstructor(WebElement.class).newInstance(el);
                } catch (Exception ex) {
                    throw exception("Can't instantiate list element");
                }
            });
            count = elements.size();
        //}
        return elements;
    }

    public int size() {
        if (count == -1)
            getAll();
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        return getAll().contains(o);
    }

    public Iterator<TType> iterator() {
        return getAll().iterator();
    }

    public Object[] toArray() {
        return getAll().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return getAll().toArray(a);
    }

    public boolean add(TType tType) {
        return elements.add(tType);
    }

    public boolean remove(Object o) {
        return elements.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return getAll().containsAll(c);
    }

    public boolean addAll(Collection<? extends TType> c) {
        return elements.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends TType> c) {
        return elements.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    public void clear() {
        elements.clear();
    }

    public TType get(int index) {
        return getAll().get(index);
    }
    public TType get(String name) {
        return first(getAll(), el -> getElementByNameAction(el.getWebElement(), name));
    }
    public TType get(Enum name) {
        return get(getEnumValue(name));
    }

    public TType set(int index, TType element) {
        return elements.set(index, element);
    }

    public void add(int index, TType element) {
        elements.add(index, element);
    }

    public TType remove(int index) {
        return elements.remove(index);
    }

    public int indexOf(Object o) {
        return getAll().indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return getAll().lastIndexOf(0);
    }

    public ListIterator<TType> listIterator() {
        return elements.listIterator();
    }

    public ListIterator<TType> listIterator(int index) {
        return elements.listIterator(index);
    }

    public List<TType> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }
}