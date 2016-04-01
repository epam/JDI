package com.epam.jdi.uitests.guitesting.unittests.pageobjects.sections;


import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Section;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JLocation;

/**
 * Created by Natalia_Grebenshchik on 1/14/2016.
 */

public class Footer extends Section {
    @JLocation(filePath = "Footer/about.png", similarity = 0.9)
    public Element about;
}
