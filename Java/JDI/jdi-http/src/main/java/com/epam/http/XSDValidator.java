package com.epam.http;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import static javax.xml.validation.SchemaFactory.newInstance;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class XSDValidator {
    private static Validator getValidator(File schemaFile) throws SAXException {
        return newInstance("http://www.w3.org/2001/XMLSchema")
                .newSchema(schemaFile).newValidator();
    }

    public static boolean validate(String inputXml, String schemaLocation) {
        try {
            Source source = new StreamSource(new StringReader(inputXml));
            getValidator(new File(schemaLocation)).validate(source);
        } catch (IOException | SAXException e) { return false; }
        return true;
    }
}
