package NotepadTests;

import com.epam.jdi.uitests.testing.notepadtests.pageobjects.NotepadApplication;
import com.epam.jdi.uitests.win.testng.testRunner.TestNGBase;
import com.epam.jdi.uitests.win.winium.elements.composite.WebSite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.notepadtests.pageobjects.NotepadApplication.mainWindow;
import static com.epam.jdi.uitests.testing.notepadtests.pageobjects.NotepadApplication.saveAsWindow;


/**
 * Created by Iuliia_Petrova on 6/3/2016.
 */
public class TestsBase extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(NotepadApplication.class);
        logger.info("Run Tests");
    }

    @Test
    public static void Test() throws InterruptedException {
        mainWindow.editText.sendKeys("This is a test");
        mainWindow.fileBtn.click();
        mainWindow.saveAsBtn.click();
        saveAsWindow.fileName.sendKeys("test.txt");
        saveAsWindow.saveBtn.click();
    }
}
