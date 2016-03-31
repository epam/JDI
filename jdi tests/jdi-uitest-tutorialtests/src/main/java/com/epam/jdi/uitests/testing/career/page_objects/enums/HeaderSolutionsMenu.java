package com.epam.jdi.uitests.testing.career.page_objects.enums;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public enum HeaderSolutionsMenu {
    PRODUCT_DEVELOPMENT("Product Development"),
    ENGINEERING_EXCELLENCE("Engineering Excellence"),
    CORE_TECHNOLOGIES("Core Technologies"),
    ASSURANCE("Assurance");

    public String value;
    HeaderSolutionsMenu(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
