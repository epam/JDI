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


import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.annotations.Title;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.IHasElement;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.*;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public class Elements<T extends IHasElement> extends BaseSelector<Enum> implements List<T> {
    private Class<T> classType;

    public Elements() {
        this(null, null);
    }

    public Elements(Class<T> classType) {
        this(null, classType);
    }
    public Elements(By byLocator) {
        this(byLocator, null);
    }

    protected boolean isSelectedAction(String name) {
        return false;
    }

    protected boolean isSelectedAction(int index) {
        return false;
    }

    protected String getValueAction() {
        return "UNDEFINED";
    }

    public Elements(By byLocator, Class<T> classType) {
        super(byLocator);
        this.classType = classType != null ? classType : (Class<T>) Button.class;
        elements = new ArrayList<>();
        useCache = true;
    }

    protected boolean getElementByNameAction(WebElement element, String name) {
        return element.getText().equals(name);
    }

    private List<T> elements;

    public List<T> listOfElements() {
        return useCache && !elements.isEmpty()
                ? elements
                : (elements = select(getElements(), el -> {
            try {
                T element = classType.newInstance();
                element.setWebElement(el);
                ((BaseElement)element).useCache = useCache;
                element.setParent(null);
                new WebCascadeInit().initElements(element, avatar.getDriverName());
                return element;
            } catch (Exception ex) {
                throw exception("Can't instantiate list element");
            }
        }));
    }

    public <E> List<E> asData(Class<E> entityClass) {
        return LinqUtils.select(listOfElements(),
            element -> asEntity(entityClass));
    }

    public int size() {
        return listOfElements().size();
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
        return listOfElements().add(tType);
    }

    public boolean remove(Object o) {
        return listOfElements().remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return listOfElements().containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return listOfElements().addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return listOfElements().addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return listOfElements().removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return listOfElements().retainAll(c);
    }

    public void clear() {
        listOfElements().clear();
    }

    public T get(int index) {
        return listOfElements().get(index);
    }

    private boolean isTextElement(Field field) {
        return field.getType().equals(Text.class) || field.getType().equals(IText.class);
    }
    public T get(String name) {
        Field[] fields = classType.getFields();
        if (where(fields, this::isTextElement).size() == 1)
            return first(listOfElements(),
                el -> ((IText)getValueField(first(el.getClass().getFields(), this::isTextElement), el))
                    .getText().equals(name));
        Field titleField = first(fields, f -> f.isAnnotationPresent(Title.class)
                && (f.getType().equals(Text.class)|| f.getType().equals(IText.class)));
        if (titleField != null)
            return first(listOfElements(),
                el -> ((IText)getValueField(getFieldWithName(el, titleField.getName()), el))
                    .getText().equals(name));
        return first(listOfElements(), el -> getElementByNameAction(el.getWebElement(), name));
    }
    private Field getFieldWithName(Object o, String name) {
        try {
            return o.getClass().getField(name);
        } catch (Exception ex) {throw exception("Can't get value from field %s", name); }
    }

    public T get(Enum name) {
        return get(getEnumValue(name));
    }

    public T set(int index, T element) {
        return listOfElements().set(index, element);
    }

    public void add(int index, T element) {
        listOfElements().add(index, element);
    }

    public T remove(int index) {
        return listOfElements().remove(index);
    }

    public int indexOf(Object o) {
        return listOfElements().indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return listOfElements().lastIndexOf(0);
    }

    public ListIterator<T> listIterator() {
        return listOfElements().listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return listOfElements().listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return listOfElements().subList(fromIndex, toIndex);
    }
}