package com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 11/16/2016.
 */
public class TableFilter {

    public String name;
    public String value;
    public CheckPageTypes type;

    public TableFilter(String template) {
        String[] split;
        if (template.matches("[^=]+\\*=[^=]*")) {
            split = template.split("\\*=");
            name = split[0];
            value = split[1];
            type = CheckPageTypes.MATCH;
            return;
        }
        if (template.matches("[^=]+~=[^=]*")) {
            split = template.split("~=");
            name = split[0];
            value = split[1];
            type = CheckPageTypes.CONTAINS;
            return;
        }
        if (template.matches("[^=]+=[^=]*")) {
            split = template.split("=");
            name = split[0];
            value = split[1];
            type = CheckPageTypes.EQUAL;
            return;
        }
        throw exception("Wrong searchCriteria for Cells: " + template);
    }
}
