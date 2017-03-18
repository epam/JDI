package com.epam.jdi.uitests.gui;

import com.epam.jdi.uitests.core.logger.JDILogger;
import com.epam.jdi.uitests.core.settings.JDISettings;

import java.io.IOException;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.commons.PropertyReader.getProperties;

/**
 * Created by Natalia_Grebenshchikova on 1/18/2016.
 */
public abstract  class GuiSettings extends JDISettings {

    public static String imageRoot;
    public static String applicationType;
    public static String webDomain;

    public static void init() {
        try {
            getProperties(jdiSettingsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fillAction(p -> imageRoot = p, "imageRoot");
        fillAction(p -> webDomain = p, "domain");
        fillAction(p -> applicationType = p, "applicationType");

        logger = new JDILogger("JDI Logger");
    }
}
