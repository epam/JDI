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


import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.web.selenium.elements.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.select;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;


/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class Elements<T extends Element> extends BaseElement implements List<T> {
    private Class<T> classType;

    public Elements() {
    }

    public Elements(Class<T> classType) {
        this.classType = classType;
    }
    public Elements(By byLocator) {
        super(byLocator);
    }
    public Elements(By byLocator, Class<T> classType) {
        super(byLocator);
        this.classType = classType;
    }

    protected boolean getElementByNameAction(WebElement element, String name) {
        return element.getText().equals(name);
    }

    private List<T> elements;

    public List<T> listOfElements() {
        return JDISettings.useCache && elements != null && !elements.isEmpty()
            ? elements
            : (elements = select(getAvatar().searchAll().getElements(), el -> {
                try {
                    return classType.getDeclaredConstructor(WebElement.class).newInstance(el);
                } catch (Exception ex) {
                    throw exception("Can't instantiate list element");
                }
            }));
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        return listOfElements().contains(o);
    }

    public Iterator<T> iterator() {
        return listOfElements().iterator();
    }

    public Object[] toArray() {
        return listOfElements().toArray();
    }

    public <E> E[] toArray(E[] a) {
        return listOfElements().toArray(a);
    }

    public boolean add(T tType) {
        return elements.add(tType);
    }

    public boolean remove(Object o) {
        return elements.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return listOfElements().containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return elements.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
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

    public T get(int index) {
        return listOfElements().get(index);
    }
    public T get(String name) {
        return first(listOfElements(), el -> getElementByNameAction(el.getWebElement(), name));
    }
    public T get(Enum name) {
        return get(getEnumValue(name));
    }

    public T set(int index, T element) {
        return elements.set(index, element);
    }

    public void add(int index, T element) {
        elements.add(index, element);
    }

    public T remove(int index) {
        return elements.remove(index);
    }

    public int indexOf(Object o) {
        return listOfElements().indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return listOfElements().lastIndexOf(0);
    }

    public ListIterator<T> listIterator() {
        return elements.listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return elements.listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }
}