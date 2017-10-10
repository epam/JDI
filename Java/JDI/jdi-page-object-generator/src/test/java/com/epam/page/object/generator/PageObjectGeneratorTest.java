package com.epam.page.object.generator;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class PageObjectGeneratorTest {

    private static final String validJSONPath = "src/test/resources/valid.json";
 
    @Test
 	public void mainTest() throws Exception {
        List<String> urls = new ArrayList<>();

        urls.add("https://www.w3schools.com/html/html_forms.asp");
        urls.add("https://www.w3schools.com/css/default.asp");
        urls.add("https://www.w3schools.com/html/html_form_input_types.asp");
        
        PageObjectGenerator pageObjectGenerator =
                new PageObjectGenerator(validJSONPath, urls, "src/test/resources/");
        
        pageObjectGenerator.generateJavaFile();
    }

}