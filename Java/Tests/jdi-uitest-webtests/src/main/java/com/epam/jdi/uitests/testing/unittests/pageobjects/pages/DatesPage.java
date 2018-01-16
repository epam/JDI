package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IFileInput;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.web.robot.RFileInput;
import com.epam.jdi.uitests.web.selenium.elements.common.DatePicker;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.ImageFile;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class DatesPage extends WebPage {
    @ImageFile("datepicker2.png")
    @FindBy(css = "#datepicker input")
    public DatePicker datepicker;
    @FindBy(css = "input[type=file]")
    public IFileInput imageInput;
    @FindBy(css = "[data-provides=fileinput]")
    public RFileInput rImageInput;
    @FindBy(css = "[class=filename] span")
    public ILabel uploadedFileName;
    @FindBy(css = "main form")
    public ContactForm contactForm;
}
