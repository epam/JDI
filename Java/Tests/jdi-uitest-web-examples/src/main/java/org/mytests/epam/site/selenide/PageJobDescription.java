package org.mytests.epam.site.selenide;

import com.epam.jdi.uitests.web.robot.JRobot;
import com.epam.jdi.uitests.web.selenium.elements.base.J;
import com.epam.web.matcher.testng.Assert;
import org.mytests.epam.site.entities.Attendee;
import org.openqa.selenium.By;

import java.io.File;
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
        find(counryList, attendee.country);
        cityValue.click();
        find(cityList, attendee.city);

        cvTextField.click();

        //It works:
        JRobot.pasteText(new File(attendee.cv).getAbsolutePath());

        //But more natural way does not work here. If for the case above we try:
        //cvTextField.sendKeys(new File(attendee.cv).getAbsolutePath());
        //Then we have "org.openqa.selenium.WebDriverException: unknown error: cannot focus element"

        /* NB!
        Since in some cases WebDriver is not able to focus to proper windows text field to enter a file path
        solution also could be found in usage of AutoIt script.
        The script will contain three commands ControlFocus(..., ControlSetText(..., ControlClick(...
        and may be executed in the test:
        RunTime.getRunTime().exec("C:\\ThePath\\AutoITScript.exe")
        For more info: https://ru.wikipedia.org/wiki/AutoIt  https://www.autoscript.com
        */

        commentTextField.click();
        commentTextField.sendKeys(attendee.comment);
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
