package com.epam.jdi.uitests.web.selenium.driver;

import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static org.apache.commons.io.FileUtils.deleteDirectory;

/**
 * Created by ryoo on 10/06/16.
 */

public class WebDriverProvider {

    public static final String FOLDER_PATH = new File("").getAbsolutePath() + "/src/main/resources/driver/";
    private static final String TEMP_FOLDER = FOLDER_PATH + "temp/";
    public static final String CHROME_DRIVER_PATH = checkOS().equals("win") ? FOLDER_PATH + "chromedriver.exe" : FOLDER_PATH + "chromedriver";
    public static final String IE_DRIVER_PATH = FOLDER_PATH + "IEDriverServer.exe";

    private static final String CHROME_STORAGE = "http://chromedriver.storage.googleapis.com/";
    private static final String CHROME_MAC_DRIVER = "chromedriver_mac32.zip";
    private static final String CHROME_NIX_DRIVER = "chromedriver_linux64.zip";
    private static final String CHROME_WIN_DRIVER = "chromedriver_win32.zip";
    private static final String CHROME_VERSION = "2.22";
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
    private static String getLatestVersion() throws IOException {
        File latestVersionFile = new File(TEMP_FOLDER + "LATEST_RELEASE");
        FileUtils.copyURLToFile(new URL(CHROME_STORAGE + "LATEST_RELEASE"), latestVersionFile);
        BufferedReader bf = new BufferedReader(new FileReader(latestVersionFile.getAbsolutePath()));
        String version = bf.readLine();
        bf.close();
        return version;
    }
    private static File recreateTempFollder() {
        File temp = new File(TEMP_FOLDER);
        temp.delete();
        temp.mkdirs();
        return temp;
    }
    private static String chromeDriverDownloadUrl() throws IOException {
        String url = CHROME_STORAGE + getLatestVersion() + "/";
        switch (checkOS()) {
            case "mac":
                return url + CHROME_MAC_DRIVER;
            case "win":
                return url + CHROME_WIN_DRIVER;
            default:
                return url + CHROME_NIX_DRIVER;
        }
    }

    public static void downloadChromeDriver() {
        if (!isInStock(CHROME_DRIVER_PATH)) {
            try {
                File temp = recreateTempFollder();
                File zip = new File(TEMP_FOLDER + "chromedriver.zip");
                FileUtils.copyURLToFile(new URL(chromeDriverDownloadUrl()), zip);
                new ZipFile(zip).extractAll(FOLDER_PATH);
                deleteDirectory(temp);
                if (checkOS().equals("mac") || checkOS().equals("nix")) {
                    Runtime.getRuntime().exec("chmod u+x " + CHROME_DRIVER_PATH);
                }
            } catch (Exception e) {
                exception("Can't get ChromeDriver. Exception: " + e.getMessage());
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
