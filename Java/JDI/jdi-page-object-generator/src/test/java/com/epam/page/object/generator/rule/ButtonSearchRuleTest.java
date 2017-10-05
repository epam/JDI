package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

public class ButtonSearchRuleTest {

    @Test
    public void Test1() throws Exception {
        ISearchRule rule = new ButtonSearchRule("a", true, Collections.singletonList("w3-btn"), Collections.emptyList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 14);
    }

    @Test
    public void Test2() throws Exception {
        ISearchRule rule = new ButtonSearchRule("button", false, null, Collections.emptyList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 1);
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").text(), "Submit");
    }

    @Test
    public void Test3() throws Exception {
        ISearchRule rule = new ButtonSearchRule("div", false,
			Arrays.asList("w3-row", "w3-center", "w3-small"), Collections.emptyList());
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 2);
    }

    @Test
    public void Test4() throws Exception {
        ISearchRule rule = new ButtonSearchRule("div", true,
			Collections.singletonList("sidesection"), Arrays.asList(new ElementAttribute("style",
			"text-align:center;"), new ElementAttribute("id", "stickypos")));
        Assert.assertEquals(rule.extractElementsFromWebSite("https://www.w3schools.com/html/html_forms.asp").size(), 1);
    }

}