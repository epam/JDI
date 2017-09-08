package com.epam.jdi.uitests.win.robot;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class JRobot {
    public static void pasteText(CharSequence text) {
        StringSelection stringSelection = new StringSelection(text.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            throw exception("Robot Input exception");
        }
    }
}
