package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

/** Created by oksana_cherniavskaia on 12.10.2018. */
public class ApplyShareCell extends Section {

  @Css(".search-result__item-apply")
  public ILink apply;

  @Css(".search-result__share-button")
  public IButton share;
}
