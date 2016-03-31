package com.epam.jdi.uitests.testing.career.page_objects.enums;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public enum JobCategories {
    QA("Software Test Engineering");

    public String value;

    JobCategories(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
