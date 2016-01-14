package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.web.selenium.elements.composite.Pagination;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 12/25/2015.
 */
public class JdiPaginator extends Pagination {
    @FindBy(css = "[class=next]  a")
    public IButton next;
    @FindBy(css = "[class=prev]  a")
    public IButton prev;
    @FindBy(css = "[class=first] a")
    public IButton first;
    @FindBy(css = "[class=last]  a")
    public IButton last;
    @FindBy(css = ".uui-pagination li")
    public IButton page;
}
