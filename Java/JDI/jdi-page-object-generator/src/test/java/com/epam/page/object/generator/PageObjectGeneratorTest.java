package com.epam.page.object.generator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PageObjectGeneratorTest {

    private static final String validJSONPath = "src/test/resources/validJSON.txt";
 
    @Test
 	public void mainTest() throws Exception {
        List<String> urls = new ArrayList<>();

        urls.add("https://www.w3schools.com/html/html_forms.asp");
        urls.add("https://www.w3schools.com/css/default.asp");
        
        PageObjectGenerator pageObjectGenerator =
                new PageObjectGenerator(validJSONPath, urls, "src/test/resources/");
        
        pageObjectGenerator.generateJavaFile();
    }

}