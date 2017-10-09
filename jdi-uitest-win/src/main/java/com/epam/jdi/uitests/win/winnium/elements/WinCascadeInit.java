package com.epam.jdi.uitests.win.winnium.elements;

import com.epam.jdi.uitests.core.interfaces.CascadeInit;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.complex.ComboBoxElement;
import com.epam.jdi.uitests.win.winnium.elements.complex.Dropdown;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class WinCascadeInit extends CascadeInit {

    @Override
    protected IBaseElement fillFromJDIAnnotation(IBaseElement instance, Field field) {
        if (instance instanceof Dropdown || instance instanceof ComboBoxElement) {
            JDropdown dropdownAnnotation = field.getAnnotation(JDropdown.class);
            if (dropdownAnnotation == null)
                return instance;

            if (instance instanceof Dropdown)
                ((Dropdown) instance).setUp(findByToBy(dropdownAnnotation.expand()),
                        findByToBy(dropdownAnnotation.elementByName()));

            if (instance instanceof ComboBoxElement)
                ((ComboBoxElement) instance).setUp(findByToBy(dropdownAnnotation.expand()),
                        findByToBy(dropdownAnnotation.elementByName()));
        }

        if (instance instanceof Table) {
            JTable tableAnnotation = field.getAnnotation(JTable.class);
            if (tableAnnotation == null)
                return instance;

            Table table = (Table)instance;

            table.setUp(findByToBy(tableAnnotation.root()), tableAnnotation.columnHeadersInTableXpath(),
                    tableAnnotation.rowsInTableXpath(), tableAnnotation.headerInRowXpath(),
                    tableAnnotation.columnsInRowXpath(), tableAnnotation.headerType(), tableAnnotation.rowStartIndex(),
                    tableAnnotation.colStartIndex(), tableAnnotation.useCache());
        }

        return instance;
    }

    @Override
    protected Class<?>[] stopTypes() {
        return new Class<?>[] {Object.class, Section.class, Element.class};
    }

    @Override
    protected void fillPageFromAnnotation(Field field, IBaseElement instance, Class<?> parentType) {
        throw exception("Not supported");
    }

    @Override
    protected IBaseElement fillInstance(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (!element.hasLocator())
            element.setAvatar(new GetElementModule(getNewLocator(field), element));
        return element;
    }

    @Override
    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type, String fieldName) throws IllegalAccessException, InstantiationException {
        By newLocator = getNewLocator(field);
        BaseElement instance = null;
        if (type.isInterface())
            type = MapInterfaceToElement.getClassFromInterface(type);
        if (type != null) {
            instance = (BaseElement) type.newInstance();
            instance.avatar.setByLocator(newLocator);
        }
        if (instance == null)
            throw exception("Unknown interface of field %s. Add relation interface -> class in VIElement.InterfaceTypeMap",
                    fieldName);
        instance.avatar.setDriverName(driverName);
        return instance;
    }

    @Override
    protected By getNewLocatorFromField(Field field) {
        return findByToBy(field.getAnnotation(FindBy.class));
    }

    private By findByToBy(FindBy locator) {
        if (locator == null)
            return null;
        if (!"".equals(locator.className()))
            return By.className(locator.className());
        if (!"".equals(locator.name()))
            return By.name(locator.name());
        if (!"".equals(locator.id()))
            return By.id(locator.id());
        if (!"".equals(locator.xpath()))
            return By.xpath(locator.xpath());
        return null;
    }
}
