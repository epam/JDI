package com.epam.jdi.uitests.web.selenium.driver;

import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.text.MessageFormat.format;
import static org.apache.commons.io.FileUtils.deleteDirectory;

/**
 * Created by ryoo on 10/06/16.
 */

public class WebDriverProvider {

    static final String FOLDER_PATH = new File("").getAbsolutePath() + "/src/main/resources/driver/";
    private static final String TEMP_FOLDER = FOLDER_PATH + "temp/";
    static final String getChromeDriverPath (String folderPath) {
        return checkOS().equals("win") ? folderPath + "/chromedriver.exe" : folderPath + "/chromedriver";
    }
    static final String getIEDriverPath (String folderPath) {
        return folderPath + "/IEDriverServer.exe";
    }
    static final String getFirefoxDriverPath (String folderPath) {
        return folderPath + "/geckodriver.exe";
    }

    private static final String CHROME_STORAGE = "http://chromedriver.storage.googleapis.com/";
    private static final String CHROME_MAC_DRIVER = "chromedriver_mac64.zip";
    private static final String CHROME_NIX_DRIVER = "chromedriver_linux64.zip";
    private static final String CHROME_WIN_DRIVER = "chromedriver_win32.zip";
    public static String DRIVER_VERSION = "";
    private static final String IE_WIN_DRIVER_URL = "http://selenium-release.storage.googleapis.com/{0}/IEDriverServer_x64_{0}.1.zip";

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
        File latestVersionFile = new File(TEMP_FOLDER + "LATEST_RELEASE.txt");
        FileUtils.copyURLToFile(new URL(CHROME_STORAGE + "LATEST_RELEASE"), latestVersionFile);
        BufferedReader bf = new BufferedReader(new FileReader(latestVersionFile.getAbsolutePath()));
        String version = bf.readLine();
        bf.close();
        return version;
    }
    private static String getVersion() throws IOException {
        return !DRIVER_VERSION.equals("")
                ? DRIVER_VERSION
                : getLatestVersion();
    }
    private static File recreateTempFollder() {
        File temp = new File(TEMP_FOLDER);
        temp.delete();
        temp.mkdirs();
        return temp;
    }

    private static String chromeDriverDownloadUrl() throws IOException {
        String url = CHROME_STORAGE + getVersion() + "/";
        switch (checkOS()) {
            case "mac":
                return url + CHROME_MAC_DRIVER;
            case "win":
                return url + CHROME_WIN_DRIVER;
            default:
                return url + CHROME_NIX_DRIVER;
        }
    }
    private static String ieDriverDownloadUrl() {
        return format(IE_WIN_DRIVER_URL, DRIVER_VERSION.equals("") ? "2.53" : DRIVER_VERSION);
    }
    private static void downloadDriver(String driverName, String driverPath, String zipName, String downloadUrl) {
        if (!isInStock(driverPath)) {
            try {
                File temp = recreateTempFollder();
                File zip = new File(TEMP_FOLDER + zipName);
                FileUtils.copyURLToFile(new URL(downloadUrl), zip);
                new ZipFile(zip).extractAll(FOLDER_PATH);
                deleteDirectory(temp);
            } catch (Exception e) {
                throw exception("Can't get %s. Exception: " + e.getMessage(), driverName);
            }
        }
    }

    public static void downloadChromeDriver(String folderPath) {
        try {
            downloadDriver("ChromeDriver", getChromeDriverPath(folderPath), "chromedriver.zip", chromeDriverDownloadUrl());
            if (checkOS().equals("mac") || checkOS().equals("nix"))
                Runtime.getRuntime().exec("chmod u+x " + getChromeDriverPath(folderPath));
        } catch (IOException e) {
            throw exception("Can't get %s. Exception: " + e.getMessage(), "ChromeDriver");
        }
    }

    public static void downloadIEDriver(String folderPath) {
        downloadDriver("IEDriver", getIEDriverPath(folderPath),
                format("IEDriverServer_x64_%s.1.zip", DRIVER_VERSION.equals("") ? "2.53" : DRIVER_VERSION),
                ieDriverDownloadUrl());
    }
}
