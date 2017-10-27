package com.epam.jdi.uitests.web.robot;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */


import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import static com.epam.commons.Timer.sleep;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.awt.event.KeyEvent.*;

/**
 * Created by Roman_Iovlev on 9/7/2015.
 */
public final class JRobot {
    private JRobot() { }

    public static void pasteText(CharSequence text) {
        try {
            Robot robot;
            try {
                robot = new Robot();
            } catch (Exception ex) {
                throw exception("Can't instantiate Robot");
            }

            sleep(1000);

            StringSelection stringSelection = new StringSelection(text.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, (clipboard1, contents) -> {
            });

            if (System.getProperty("os.name").toLowerCase().contains("mac")) {

                /*robot.keyPress(VK_META);
                robot.keyPress(VK_TAB);
                robot.keyRelease(VK_TAB);
                robot.keyRelease(VK_META);

                robot.delay(1000);*/

                //use Apple script to focus on browser, because browser loses focus when Java app launches
                Capabilities cap = ((RemoteWebDriver) WebSettings.getDriver()).getCapabilities();
                String browserName = cap.getBrowserName();
                Runtime runtime = Runtime.getRuntime();
                String tell = "tell app \"" + browserName + "\" to activate";
                String[] args = { "osascript", "-e", tell };
                Process process = runtime.exec(args);

                robot.delay(1000);

                robot.keyPress(VK_META);
                robot.keyPress(VK_SHIFT);
                robot.keyPress(VK_G);
                robot.keyRelease(VK_G);
                robot.keyRelease(VK_SHIFT);
                robot.keyRelease(VK_META);

                robot.keyPress(VK_META);
                robot.keyPress(VK_V);
                robot.keyRelease(VK_V);
                robot.keyRelease(VK_META);

                robot.keyPress(VK_ENTER);
                robot.keyRelease(VK_ENTER);

                robot.delay(1000 * 2);

                robot.keyPress(VK_ENTER);
                robot.keyRelease(VK_ENTER);

            }
            else {
                    robot.keyPress(VK_CONTROL);
                    robot.keyPress(VK_V);
                    robot.keyRelease(VK_V);
                    robot.keyRelease(VK_CONTROL);

                    robot.keyPress(VK_ENTER);
                    robot.keyRelease(VK_ENTER);
            }

        } catch (Exception ex) {
            throw exception("Robot Input exception");
        }
    }
}