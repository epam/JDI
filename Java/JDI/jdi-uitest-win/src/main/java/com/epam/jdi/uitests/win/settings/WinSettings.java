package com.epam.jdi.uitests.win.settings;

import com.epam.commons.PropertyReader;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.core.interfaces.complex.ICheckList;
import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.core.interfaces.complex.IRadioButtons;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.IEntityTable;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITable;
import com.epam.jdi.uitests.core.logger.JDILogger;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.TestNGCheck;
import com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.SelectElement;
import com.epam.jdi.uitests.win.winnium.elements.common.Button;
import com.epam.jdi.uitests.win.winnium.elements.common.CheckBox;
import com.epam.jdi.uitests.win.winnium.elements.common.Label;
import com.epam.jdi.uitests.win.winnium.elements.common.TextBox;
import com.epam.jdi.uitests.win.winnium.elements.complex.CheckList;
import com.epam.jdi.uitests.win.winnium.elements.complex.ComboBoxElement;
import com.epam.jdi.uitests.win.winnium.elements.complex.Dropdown;
import com.epam.jdi.uitests.win.winnium.elements.complex.RadioButtons;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;

import java.awt.*;
import java.io.IOException;

public class WinSettings extends JDISettings {

    public static void initFromProperties()  throws IOException {
        JDISettings.driverFactory = new WiniumDriverFactory();
        asserter = new TestNGCheck();
        PropertyReader.getProperties(jdiSettingsPath);
        logger = new JDILogger("JDI Logger");
        MapInterfaceToElement.init(defaultInterfacesMap);

        JDISettings.driverFactory.setDriverPath(PropertyReader.getProperty("drivers.folder"));

        JDISettings.initFromProperties();
    }

    private static Object[][] defaultInterfacesMap = new Object[][]{
            {ITextArea.class, TextBox.class},
            {IButton.class, Button.class},
            {IDropDown.class, Dropdown.class},
            {IElement.class, Element.class},
            {IButton.class, Button.class},
            {IComboBox.class, ComboBoxElement.class},
            {ITextArea.class, TextArea.class},
            {ILabel.class, Label.class},
            {IDropDown.class, Dropdown.class},
            {ITable.class, Table.class},
            {IEntityTable.class, EntityTable.class},
            {ICheckBox.class, CheckBox.class},
            {IRadioButtons.class, RadioButtons.class},
            {ICheckList.class, CheckList.class},
            {ISelect.class, SelectElement.class},
    };
}
