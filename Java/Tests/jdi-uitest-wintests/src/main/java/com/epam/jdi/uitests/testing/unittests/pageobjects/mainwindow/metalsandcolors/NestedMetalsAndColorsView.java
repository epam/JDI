package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.unittests.enums.Colors;
import com.epam.jdi.uitests.testing.unittests.enums.Metals;
import com.epam.jdi.uitests.testing.unittests.enums.Nature;
import com.epam.jdi.uitests.testing.unittests.enums.Odds;
import com.epam.jdi.uitests.win.winnium.elements.common.CheckBox;
import com.epam.jdi.uitests.win.winnium.elements.complex.CheckList;
import com.epam.jdi.uitests.win.winnium.elements.complex.ComboBoxElement;
import com.epam.jdi.uitests.win.winnium.elements.complex.RadioButtons;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.JDropdown;
import org.openqa.selenium.support.FindBy;

public class NestedMetalsAndColorsView extends Section {
    @FindBy(id = "calculateMButton")
    public IButton calculateButton;

    @FindBy(id = "waterMCheckBox")
    public CheckBox cbWater;

    @JDropdown(expand = @FindBy(id = "metalsMComboBox"),
            elementByName = @FindBy(xpath = ".//*[contains(@ControlType,'ControlType.Text')]"))
    public ComboBoxElement<Metals> metalsComboBox;

    @FindBy(xpath = ".//*[contains(@ControlType,'ControlType.CheckBox')]")
    public CheckList<Nature> natureCheckList;

    @JDropdown(expand = @FindBy(id = "colorsMComboBox"),
            elementByName = @FindBy(xpath = ".//*[contains(@ControlType,'ControlType.Text')]"))
    public IDropDown<Colors> colors;

    @FindBy(xpath = ".//*[contains(@ControlType,'ControlType.RadioButton') and @Name mod 2 = 1]")
    public RadioButtons<Odds> oddsR;
}
