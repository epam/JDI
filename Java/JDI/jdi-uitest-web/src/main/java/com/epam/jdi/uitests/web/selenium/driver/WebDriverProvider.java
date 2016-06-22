package com.epam.jdi.uitests.web.selenium.driver;

import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by ryoo on 10/06/16.
 */

public class WebDriverProvider {

    public static final String FOLDER_PATH = new File("").getAbsolutePath() + "/Java/build/driver/";
    public static final String CHROME_DRIVER_PATH = checkOS().equals("win") ? FOLDER_PATH + "chromedriver.exe" : FOLDER_PATH + "chromedriver";
    public static final String IE_DRIVER_PATH = FOLDER_PATH + "IEDriverServer.exe";

    private static final String CHROME_MAC_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_mac32.zip";
    private static final String CHROME_NIX_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_linux64.zip";
    private static final String CHROME_WIN_DRIVER_URL = "http://chromedriver.storage.googleapis.com/2.22/chromedriver_win32.zip";
    private static final String IE_WIN_DRIVER_URL     = "http://selenium-release.storage.googleapis.com/2.53/IEDriverServer_x64_2.53.1.zip";

    private static Boolean isInStock(String driver) {
        File path = new File(FOLDER_PATH);
        if (!path.exists()) {
            path.mkdirs();
            return false;
        }
        return new File(driver).exists();
    }

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
        if (!isInStock(CHROME_DRIVER_PATH)) {
            try {
                File zip = new File(FOLDER_PATH + "chromedriver.zip");
                String url;

                if (checkOS().equals("mac")) {
                    url = CHROME_MAC_DRIVER_URL;
                } else if (checkOS().equals("win")) {
                    url = CHROME_WIN_DRIVER_URL;
                } else {
                    url = CHROME_NIX_DRIVER_URL;
                }

                FileUtils.copyURLToFile(new URL(url), zip);
                ZipFile zipFile = new ZipFile(zip);
                zipFile.extractAll(FOLDER_PATH);
                zip.delete();
                if (checkOS().equals("mac") || checkOS().equals("nix")) {
                    Runtime.getRuntime().exec("chmod u+x " + CHROME_DRIVER_PATH);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadIEDriver() {
        if (!isInStock(IE_DRIVER_PATH)) {
            try {
                File zip = new File(FOLDER_PATH + "IEDriverServer_x64_2.53.1.zip");
                FileUtils.copyURLToFile(new URL(IE_WIN_DRIVER_URL), zip);
                ZipFile zipFile = new ZipFile(zip);
                zipFile.extractAll(FOLDER_PATH);
                zip.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
