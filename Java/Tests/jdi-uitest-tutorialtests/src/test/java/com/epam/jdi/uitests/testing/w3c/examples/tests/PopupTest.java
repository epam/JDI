package com.epam.jdi.uitests.testing.w3c.examples.tests;

import org.testng.annotations.Test;

import static com.epam.jdi.site.w3c.W3CSite.homePage;
import static com.epam.jdi.site.w3c.W3CSite.likeForm;

public class PopupTest extends W3cInitTests{
    @Test
    public void popupTest(){
        homePage.open();
        homePage.like.click();
        likeForm.close();
    }
}
