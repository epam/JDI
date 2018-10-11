package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.functions.CloseButton;
import com.epam.jdi.uitests.core.annotations.functions.OkButton;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Popup;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

import static com.epam.jdi.site.epam.EpamSite.acceptCookiePopup;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static java.lang.String.format;

public class AcceptCookiePopup extends Popup {
  @OkButton
  @CloseButton
  @Css(".cookie-disclaimer__button")
  public Button accept;

  @Css(".cookie-disclaimer__description")
  public IText description;

  @Css(".cookie-disclaimer__description a")
  ILink learnMore;

  public void shouldNotBeDisplayed() {
    if (acceptCookiePopup.verifyElementPresent()) {
      acceptCookiePopup.accept.click();
    }
    acceptCookiePopup.checkNonePresentOnPage();

  }
}
