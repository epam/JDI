package com.epam.jdi.uitests.web.selenium.utils;

/*
 * Uses Sikuli API for comparing provided images with specified web browser layout.
 */

import org.sikuli.script.Screen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Layout {

    public static boolean verifyLayout = false;
    private static final Set<String> EXTENSIONS = new HashSet<>(Arrays.asList(".jpg", ".jpeg", ".png"));

    public static boolean verify(String pathToFile) {
        if (!verifyLayout || isBlank(pathToFile)) return true;
        if (!verifyImageFormat(pathToFile)) {
            logger.info(format("'%s' is not .jpg, ,jpeg or .png file.", pathToFile));
            return false;
        }
        return findMatch(pathToFile);
    }
    /**
     * Verifies that provided files are in .jpg, .jpeg or .png formats.
     * This is a kind of compulsory measure, as Sikuli handles non-image types incorrectly.
     *
     * @param  pathToImage path to image: C:/Screenshots/img1.jpeg
     */
    private static boolean verifyImageFormat(String pathToImage) {
        int dot = pathToImage.lastIndexOf(".");
        if (dot > 0) {
            String ending = pathToImage.substring(dot);
            return EXTENSIONS.contains(ending.toLowerCase());
        }
        return false;
    }

    /**
     * Returns Match object, if a match was found.
     *
     * @return null, if match was not found.
     */
    private static boolean findMatch(String pathToFile) {
        if (!driverFactory.hasRunDrivers()) {
            logger.info("Driver not run");
            return false;
        }
        return screen.exists(pathToFile) != null;
    }
    private static Screen screen = new Screen();
}
