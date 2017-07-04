package org.mytests.epam.site.selenide;

import com.epam.jdi.uitests.web.selenium.elements.base.J;
import com.epam.web.matcher.junit.Assert;
import org.mytests.epam.site.entities.Attendee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.jdi.uitests.web.selenium.elements.base.JdiStatic.*;


/**
 * Created by Roman_Iovlev on 2/21/2017.
 */
public class PageJobDescription {
    public static PageJobDescription pageJobDescription
            = new PageJobDescription();

    J nameTextField = $("[placeholder='First Name']"),
      lastNameTextField = $("[placeholder='Last Name']"),
      emailField = $("[placeholder='Email']"),
      cvTextField = $(".file-upload"),
      commentTextField = $(".comment-input"),
      submitButton = $(By.xpath("//*[.='Submit']"));

    J counryValue = $(".country-wrapper [id$=-container]");
    List<J> counryList = $$("[id*='applicantCountry'] li");

    J cityValue = $(".city-wrapper [id$=-container]");
    List<J> cityList = $$("[id*='applicantCity'] li");

    public void submitForm(Attendee attendee) {

        nameTextField.sendKeys(attendee.name);
        lastNameTextField.sendKeys(attendee.lastName);
        emailField.sendKeys(attendee.email);
        counryValue.click();
        find(counryList, attendee.country).click();
        cityValue.click();
        find(cityList, attendee.city).click();
        cvTextField.sendKeys(attendee.cv);
        commentTextField.sendKeys(attendee.comment);
        submitButton.click();
    }

    public void verifyCVForm(Attendee attendee) {
        Assert.areEquals(nameTextField.getValue(), attendee.name);
        Assert.areEquals(lastNameTextField.getValue(), attendee.lastName);
        Assert.areEquals(emailField.getValue(), attendee.email);
        Assert.areEquals(counryValue.getText(), attendee.country);
        Assert.areEquals(cityValue.getText(), attendee.city);
        Assert.areEquals(commentTextField.getValue(), attendee.comment);
    }
}
