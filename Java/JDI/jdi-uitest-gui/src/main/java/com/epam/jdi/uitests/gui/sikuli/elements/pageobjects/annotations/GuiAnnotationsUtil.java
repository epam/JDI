package com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations;

import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.gui.GuiSettings;
import com.epam.jdi.uitests.gui.sikuli.elements.CheckPageTypes;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Page;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.JPage;
import org.sikuli.script.Pattern;


import java.awt.*;

import static com.epam.jdi.uitests.gui.sikuli.elements.CheckPageTypes.*;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
public class GuiAnnotationsUtil extends AnnotationsUtil {
    public static void fillPageFromAnnotation(Page element, JPage pageAnnotation, Class<?> parentClass) {
        String filePath = GuiSettings.imageRoot + pageAnnotation.fileLogoPath();
        int x = pageAnnotation.rectangle()[0];
        int i = pageAnnotation.rectangle()[1];
        int w = pageAnnotation.rectangle()[2];
        int h = pageAnnotation.rectangle()[3];
        Rectangle rectangle = new Rectangle(x, i, w, h);
        double similarity = pageAnnotation.similarity();

        CheckPageTypes checkPage = pageAnnotation.checkPage();
        if (checkPage == NONE)
            checkPage = (checkPage != NONE) ? checkPage : EQUAL;

        element.updatePageData(filePath, checkPage, rectangle, x, i, w, h, similarity);
    }

    public static Pattern getPattern(JLocation locator, JOffset offset) {
        if (locator == null) return null;
        Pattern pattern = new Pattern(GuiSettings.imageRoot + locator.filePath());
        pattern = pattern.similar((float) locator.similarity());
        if (offset != null)
            switch (offset.offsetUnit()) {
                case PIXELS:
                    pattern.targetOffset(offset.xOffset(), offset.yOffset());
                    break;
                case PERCENTAGE:
                    int x = (int) pattern.getImage().getSize().getWidth() * offset.xOffset() / 100;
                    int y = (int) pattern.getImage().getSize().getHeight() * offset.yOffset() / 100;
                    pattern.targetOffset(x, y);
                    break;
            }
        return pattern;
    }

    public static String getFilePath(JLocation locator) {
        if (locator == null) return null;
        return GuiSettings.imageRoot + locator.filePath();
    }

    public static double getSimilarity(JLocation location) {
        return location.similarity();
    }

    public static int getX(JRegion region) {
        return region.rectangle()[0];
    }

    public static int getY(JRegion region) {
        return region.rectangle()[1];
    }

    public static int getWight(JRegion region) {
        return region.rectangle()[2];
    }

    public static int getHeight(JRegion region) {
        return region.rectangle()[3];
    }

    public static Rectangle getRectangle(JRegion region) {
        int[] arr = region.rectangle();
        return new Rectangle(arr[0], arr[1], arr[2], arr[3]);
    }
}
