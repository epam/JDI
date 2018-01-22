package com.epam.jdi.site.epam.sections;

import com.epam.jdi.entities.Attendee;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.robot.RFileInput;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Attribute;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ByTitle;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.XPath;

/**
 * Created by Roman_Iovlev on 10/23/2015.
 */
public class AddCVForm extends Form<Attendee> {

    @Css("[placeholder='First Name*']")
    ITextField name;
    @Css("[placeholder='Last Name*']")
    ITextField lastName;
    @Attribute(name = "placeholder", value = "Email*")
    ITextField email;
    //    @JDropdown(
//            jroot = @JFindBy(className="country-field"),
//            jvalue = @JFindBy("[class='arrow']"),
//            jlist = @JFindBy(xpath = "div[@class='select-box-results']//li[contains(@id,'applicantCountry') and .='%s']")
//    )
//    public IDropDown country;
//
//    @JDropdown(
//            jroot = @JFindBy(className = "city-field"),
//            jexpand = @JFindBy(className = "arrow"),
//            jlist = @JFindBy(css = "*root*li[id*=applicantCity]")
//            //jlist = @JFindBy(xpath = "*root*//*[contains(@id,'select-box-applicantCity')]//li")
//    )
//    IDropDown city;

//    @Css(".file-upload")
//    RFileInput cv;

    @Css(".comment__input")
    ITextArea comment;

    @Css("button.button-ui")
    IButton submit;

//    @XPath("//*[.='Cancel']")
//    IButton cancel;

//    @ByTitle("Reload")
//    public IButton reload;
}
