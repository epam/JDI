package com.epam.jdi.uitests.win.robot;
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
            StringSelection stringSelection = new StringSelection(text.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, (clipboard1, contents) -> {
            });
            sleep(1000);
            robot.keyPress(VK_CONTROL);
            robot.keyPress(VK_V);

            robot.keyRelease(VK_CONTROL);
            robot.keyPress(VK_ENTER);
            robot.keyRelease(VK_ENTER);
        } catch (Exception ex) {
            throw exception("Robot Input exception");
        }
    }
}