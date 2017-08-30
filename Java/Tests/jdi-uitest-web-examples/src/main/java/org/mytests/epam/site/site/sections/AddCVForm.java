package org.mytests.epam.site.site.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.robot.RFileInput;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.web.matcher.testng.Assert;
import org.mytests.epam.site.entities.Attendee;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/23/2015.
 */
public class AddCVForm extends Form<Attendee> {
    public AddCVForm() { super(Attendee.class); }

    @FindBy(css = "[placeholder='First Name']")
    ITextField name;
    @FindBy(css = "[placeholder='Last Name']")
    ITextField lastName;
    @FindBy(css = "[placeholder='Email']")
    ITextField email;
    @JDropdown(
        value = @JFindBy(css = ".country-wrapper [id$=-container]"),
            // %s template daster with method in 200 times
        list = @JFindBy(xpath = "//*[contains(@id,'applicantCountry')]//li[.='%s']"))
    IDropDown country;

    @JDropdown(
        value = @JFindBy(css = ".city-wrapper [id$=-container]"),
        list = @JFindBy(css = "[id*='applicantCity'] li"))
    IDropDown city;

    @FindBy(css = ".file-upload")
    RFileInput cv;
    @FindBy(css = ".comment-input")
    ITextArea comment;

    @FindBy(xpath = "//*[.='Submit']")
    IButton submit;



    @Override
    public void check(Attendee attendee){
        Assert.areEquals(name.getValue(), attendee.name);
        Assert.areEquals(lastName.getValue(), attendee.lastName);
        Assert.areEquals(email.getValue(), attendee.email);
        Assert.areEquals(country.getText(), attendee.country);
        Assert.areEquals(city.getText(), attendee.city);
        Assert.areEquals(comment.getValue(), attendee.comment);
    }


}
