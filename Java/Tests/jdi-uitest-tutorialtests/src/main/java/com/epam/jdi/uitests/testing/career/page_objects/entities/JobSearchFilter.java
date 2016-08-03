package com.epam.jdi.uitests.testing.career.page_objects.entities;

import com.epam.jdi.uitests.testing.career.page_objects.enums.JobCategories;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobCategories.QA;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.Locations.SAINT_PETERSBURG;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobSearchFilter {
    public CharSequence keywords = "QA";
    public JobCategories category = QA;
    public String location = SAINT_PETERSBURG.value;
}
