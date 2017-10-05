package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ButtonSearchRuleTest {
    @Test
    public void Test1() throws Exception {
        ISearchRule rule = new ButtonSearchRule("a", true, Arrays.asList("w3-btn"), Arrays.asList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 14);
    }

    @Test
    public void Test2() throws Exception {
        ISearchRule rule = new ButtonSearchRule("button", false, null, Arrays.asList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 1);
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").text(), "Submit");
    }

    @Test
    public void Test3() throws Exception {
        ISearchRule rule = new ButtonSearchRule("div", false, Arrays.asList("w3-row", "w3-center", "w3-small"), Arrays.asList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 2);
    }

    @Test
    public void Test4() throws Exception {
        ISearchRule rule = new ButtonSearchRule("div", true, Arrays.asList("sidesection"), Arrays.asList(new ElementAttribute("style", "text-align:center;"), new ElementAttribute("id", "stickypos")));
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 1);
    }
}