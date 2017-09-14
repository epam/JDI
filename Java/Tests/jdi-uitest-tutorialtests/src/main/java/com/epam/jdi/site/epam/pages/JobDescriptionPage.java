package com.epam.jdi.site.epam.pages;

import com.epam.jdi.site.epam.sections.AddCVForm;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ByName;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobDescriptionPage extends WebPage {

    @Css(".form-constructor") public AddCVForm addCVForm;

    @ByName("captcha") public IElement captcha;

}
