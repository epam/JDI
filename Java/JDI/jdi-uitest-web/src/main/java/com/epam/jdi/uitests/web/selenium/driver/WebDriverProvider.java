package com.epam.jdi.uitests.web.selenium.driver;

import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by ryoo on 10/06/16.
 */

public class WebDriverProvider {

    public static final String FOLDER_PATH = new File("").getAbsolutePath() + "/JDI/jdi-uitest-web/src/main/resources/driver/";
    private static final String DRIVER_PATH = checkOS().equals("win") ? FOLDER_PATH + "chromedriver.exe" : FOLDER_PATH + "chromedriver";
            
    private static final String MAC_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_mac32.zip";
    private static final String NIX_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_linux64.zip";
    private static final String WIN_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_win32.zip";

    private static Boolean isInStock() { return new File(DRIVER_PATH).exists(); }

    private static String checkOS() {

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            return "mac";
        } else if (osName.contains("win") || osName.contains("ms")) {
            return "win";
        } else {
            return "nix";
        }
    }

    public static void downloadChromeDriver() {
        if (!isInStock()) {
            try {
                File zip = new File(FOLDER_PATH + "chromedriver.zip");
                String url;

                if (checkOS().equals("mac")) {
                    url = MAC_DRIVER_URL;
                } else if (checkOS().equals("win")) {
                    url = WIN_DRIVER_URL;
                } else {
                    url = NIX_DRIVER_URL;
                }

                FileUtils.copyURLToFile(new URL(url), zip);
                ZipFile zipFile = new ZipFile(zip);
                zipFile.extractAll(FOLDER_PATH);
                zip.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
