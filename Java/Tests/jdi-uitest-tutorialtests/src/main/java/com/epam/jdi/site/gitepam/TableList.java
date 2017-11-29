package com.epam.jdi.site.gitepam;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

import java.util.List;

/**
 * Created by Roman_Iovlev on 11/14/2017.
 */
public class TableList extends Section {
    @Css("thead th") public static List<Text> headers;
}
