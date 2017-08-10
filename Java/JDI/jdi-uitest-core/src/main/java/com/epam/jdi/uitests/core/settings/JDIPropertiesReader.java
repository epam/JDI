package com.epam.jdi.uitests.core.settings;

import com.epam.commons.PropertyReader;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Roman_Iovlev on 8/10/2017.
 */
public class JDIPropertiesReader {

    public static Properties getProperties(String path) throws IOException {
        Properties p = PropertyReader.getProperties("/../../target/classes/" + path);
        return p.size() > 0 ? p : PropertyReader.getProperties(path);
    }

}
