package com.epam.jdi.uitests.win.winnium.elements.composite;

import com.epam.commons.LinqUtils;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.annotations.Mandatory;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;
import com.epam.jdi.uitests.core.interfaces.complex.FormFilters;
import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.GetElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.StringUtils.namesEqual;
import static com.epam.jdi.uitests.core.annotations.AnnotationsUtil.getElementName;
import static com.epam.jdi.uitests.core.interfaces.complex.FormFilters.ALL;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.web.matcher.base.PrintUtils.objToSetValue;
import static java.lang.String.format;

public class Form<T> extends Element implements IForm<T> {
    protected Class<T> entityClass;
    private GetElement getElement = new GetElement(this);

    private FormFilters filter = ALL;
    public void filter(FormFilters filter) {
        this.filter = filter;
    }
    private List<Field> allFields() {
        switch (filter) {
            case MANDATORY:
                return LinqUtils.where(getFields(this, ISetValue.class),
                        field -> field.isAnnotationPresent(Mandatory.class));
            case OPTIONAL:
                return LinqUtils.where(getFields(this, ISetValue.class),
                        field -> !field.isAnnotationPresent(Mandatory.class));
            default:
                return getFields(this, ISetValue.class);
        }
    }

    @Override
    public final void fill(MapArray<String, String> map) {
        foreach(allFields(), element -> {
            String fieldValue = map.first((name, value) ->
                    namesEqual(name, getElementName(element)));
            if (fieldValue == null)
                return;
            ISetValue setValueElement = (ISetValue) getValueField(element, this);
            doActionRule.accept(fieldValue, val -> setValueAction(val, setValueElement));
        });
        filter = ALL;
    }

    @Override
    public List<String> verify(MapArray<String, String> objStrings) {
        List<String> compareFalse = new ArrayList<>();
        getFields(this, IHasValue.class).stream().forEach(field -> {
            String fieldValue = objStrings.first((name, value) -> GetElement.namesEqual(name, getElementName(field)));
            if (fieldValue == null)
                return;
            IHasValue valueField = (IHasValue) getValueField(field, this);
            String actual = valueField.getValue().trim();
            if (!actual.equals(fieldValue))
                compareFalse.add(format("Field '%s' (Actual: '%s' <> Expected: '%s')", field.getName(), actual,
                        fieldValue));
        });

        return compareFalse;
    }

    @Override
    public void check(MapArray<String, String> objStrings) {
        List<String> result = verify(objStrings);
        if (result.size() > 0)
            throw exception("Check form failed:" + LINE_BREAK + print(result, LINE_BREAK));
    }

    private void setText(String text) {
        Field field = getFields(this, ISetValue.class).get(0);
        ISetValue setValueElement = (ISetValue) getValueField(field, this);
        setValueElement.setValue(text);
    }

    @Override
    public void submit(String text) {
        setText(text);
        getElement.getButton("submit").click();
    }

    @Override
    public void submit(String text, String buttonName) {
        setText(text);
        getElement.getButton(buttonName).click();
    }

    @Override
    public void submit(T entity, String buttonName) {
        fill(objToSetValue(entity));
        getElement.getButton(buttonName).click();
    }

    @Override
    public void submit(T entity, Enum buttonName) {
        submit(entity, buttonName.toString().toLowerCase());
    }

    public T getEntity() {
        return asEntity(entityClass);
    }

    @Override
    public void submit(MapArray<String, String> objStrings) {
        fill(objStrings);
        getElement.getButton("submit").click();
    }

    private String getValueAction() {
        return print(getFields(this, IHasValue.class).stream().
                map(field -> ((IHasValue) getValueField(field, this)).getValue()).collect(Collectors.toList()));
    }

    protected void setValueAction(String text, ISetValue element) {
        element.setValue(text);
    }

    @Override
    public void setValue(String value) {
        invoker.doJAction("Get value", () -> setValueAction(value, this), this.toString());
    }

    @Override
    public String getValue() {
        return invoker.doJActionResult("Get value", this::getValueAction, this.toString());
    }
}
