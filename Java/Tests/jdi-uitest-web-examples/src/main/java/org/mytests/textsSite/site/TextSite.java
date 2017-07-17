package org.mytests.textsSite.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import org.mytests.textsSite.site.pages.TextPage;

import java.io.File;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class TextSite extends WebSite {

    //In the test we open local html file
    public final static String  htmlPageSavedPath = new File("src/test/resources/Contact Form.html").getAbsolutePath();
    public static TextPage textsPage = new TextPage("file:///"+htmlPageSavedPath);

}
