package com.epam.jdi.uitests.gui.sikuli.elements.composite;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;

import java.util.List;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
public class Form<T> extends Element implements IForm<T> {
    @Override
    public void fill(MapArray<String, String> map) {

    }

    @Override
    public List<String> verify(MapArray<String, String> map) {
        return null;
    }

    @Override
    public void check(MapArray<String, String> map) {

    }

    @Override
    public void submit(String text) {

    }

    @Override
    public void submit(String text, String buttonName) {

    }

    @Override
    public void submit(T entity, String buttonName) {

    }

    @Override
    public void submit(T entity, Enum buttonName) {

    }

    @Override
    public void submit(MapArray<String, String> objStrings) {

    }


    @Override
    public void setValue(String value) {

    }

    @Override
    public String getValue() {
        return null;
    }
}
