package com.epam.jdi.uitests.core.settings;

/*
 * Uses Sikuli API for comparing provided images with specified web browser layout.
 */

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class Layout {
    public static boolean shouldVerifyLayout = false;
    public static String rootImagesPath = "src/test/resources/layout";

    private static final Set<String> EXTENSIONS = new HashSet<>(
        Arrays.asList(".jpg", ".jpeg", ".png"));

    public static boolean verify(String pathToFile) {
        assertPathToFile(pathToFile);
        return findMatch(pathToFile);
    }

    public static boolean verify(String pathToFile, int similatiryPercent) {
        assertPathToFile(pathToFile);
        return findMatch(pathToFile, similatiryPercent);
    }
    private static void assertPathToFile(String pathToFile) {
        if (!fileExist(pathToFile))
            throw exception("Can't find image in path '%s' to verify", pathToFile);
        if (!fileFormatIsCorrect(pathToFile))
            throw exception("Image file '%s' format is incorrect. Acceptable files: .jpg, .jpeg and .png", pathToFile);
    }

    /**
     * Verifies that provided files are in .jpg, .jpeg or .png formats. This is a kind of compulsory
     * measure, as Sikuli handles non-image types incorrectly.
     *
     * @param pathToImage path to image: C:/Screenshots/img1.jpeg
     */
    private static boolean fileFormatIsCorrect(String pathToImage) {
        int dot = pathToImage.lastIndexOf(".");
        return dot > 0 && EXTENSIONS.contains(pathToImage.substring(dot).toLowerCase());
    }

    /**
     *
     * @param path
     */
    public static boolean fileExist(String path) {
        return path != null && new File(path).exists();
    }

    /**
     * Returns Match object, if a match was found.
     *
     * @return null, if match was not found.
     */
    private static boolean findMatch(String pathToFile) {
        if (!driverFactory.hasRunDrivers())
            throw exception("Driver not run");
        return screen.exists(pathToFile) != null;
    }

    /**
     * @param similarityPercent - the minimum similarity to use in a find operation. The value should be between 0 and 100
     * @return true, if match was not found.
     */
    private static boolean findMatch(String pathToFile, int similarityPercent) {
        Pattern file = new Pattern(pathToFile);
        boolean result = false;
        if (!driverFactory.hasRunDrivers())
            throw exception("Driver not run");
        try {
            screen.wait(file.similar((float) similarityPercent / 100));
            result = true;
        } catch (FindFailed ignored) {
        }
        return result;
    }
    private static Screen screen = new Screen();
}
