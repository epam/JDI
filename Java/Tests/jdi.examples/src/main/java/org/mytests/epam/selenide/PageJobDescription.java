package org.mytests.epam.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.web.matcher.junit.Assert;
import org.mytests.epam.entities.Attendee;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Roman_Iovlev on 2/21/2017.
 */
public class PageJobDescription {
    public static PageJobDescription pageJobDescription = new PageJobDescription();

    SelenideElement nameTextField = $("[placeholder='First Name']");
    SelenideElement lastNameTextField = $("[placeholder='Last Name']");
    SelenideElement emailField = $("[placeholder='Email']");

    SelenideElement counryValue = $(".country-wrapper [id$=-container]");
    ElementsCollection counryList = $$("[id*='applicantCountry'] li");

    SelenideElement cityValue = $(".city-wrapper [id$=-container]");
    ElementsCollection cityList = $$("[id*='applicantCity'] li");

    SelenideElement cvTextField = $(".file-upload");
    SelenideElement commentTextField = $(".comment-input");
    SelenideElement submitButton = $(By.xpath("//*[.='Submit']"));

    public void submitForm(Attendee attendee) {
        nameTextField.sendKeys(attendee.name);
        lastNameTextField.sendKeys(attendee.lastName);
        emailField.sendKeys(attendee.email);
        counryValue.click();
        counryList.find(text(attendee.country)).click();
        cityValue.click();
        cityList.find(text(attendee.city)).click();
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
