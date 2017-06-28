package com.epam.jdi.uitests.testing.unittests.custom;

import com.epam.jdi.uitests.testing.unittests.enums.Nature;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;

/**
 * Created by Aleksandr_Shiganov on 6/21/2017.
 */

/*
The JDI CheckList adapted for the HTML pattern from page https://jdi-framework.github.io/tests/page2.htm:
---------------------------------------------------------------------------------------------------------
<section id="elements-checklist" class="vertical-group">
<p class="checkbox">
<input id="g1" class="uui-form-element blue" type="checkbox"/>
<label for="g1">Water</label>
</p>
<p class="checkbox">
<input id="g2" class="uui-form-element dark-blue" type="checkbox"/>
<label for="g2">Earth</label>
</p>
<p class="checkbox">
<input id="g3" class="uui-form-element red" type="checkbox"/>
<label for="g3">Wind</label>
</p>
<p class="checkbox">
<input id="g4" class="uui-form-element red" type="checkbox"/>
<label for="g4">Fire</label>
</p>
</section>

Since areSelected() is final the method was changed to getSelected() used for the same purpose

*/

public class CheckListOfTypeOne extends CheckList<Nature> {


    private List<WebElement> elementS, labelS, inputS;

    private String xPathCheckBoxLocator="//*[@id='elements-checklist']/p[@class='checkbox']";
    private String namesShortLocator ="/label";
    private String inputShortLocator ="/input";
    private String xPathOptionsNamesLocator, xPathOptionsInputsLocator;

    public CheckListOfTypeOne(String xPathCheckBoxLocator, String namesShortLocator, String inputShortLocator) {
        super(By.xpath(xPathCheckBoxLocator+namesShortLocator));

        this.xPathCheckBoxLocator=xPathCheckBoxLocator;
        this.namesShortLocator=namesShortLocator;
        this.inputShortLocator=inputShortLocator;
        this.xPathOptionsNamesLocator=xPathCheckBoxLocator+namesShortLocator;
        this.xPathOptionsInputsLocator=xPathCheckBoxLocator+inputShortLocator;

    }

    //since method areSelected is final and can not be overridden we will use getSelected()
    public List<String> getSelected() {
        return getDriver().findElements(By.xpath(xPathOptionsInputsLocator)).stream()
                          .filter(e -> e.isSelected())
                          .map(s -> s.findElement(By.xpath(".//following-sibling::label")).getText())
                          .collect(Collectors.toList());

    }


    public List<String> getDeselected() {
        return getDriver().findElements(By.xpath(xPathOptionsInputsLocator)).stream()
                .filter(e -> !e.isSelected())
                .map(s -> s.findElement(By.xpath(".//following-sibling::label")).getText())
                .collect(Collectors.toList());

    }


    //Looking for checkbox button we need, using its label's text
    public By formCheckButtonLabelByXPass ( String buttonName, String partOfCheckButtonXPath){
        return By.xpath(partOfCheckButtonXPath+"[contains(text(),'"+buttonName+"')]");
    }

    //Looking for checkbox button's 'input part', using its label's text
    public By formCheckButtonInputByXPass ( String buttonName, String partOfCheckButtonXPath){
        return By.xpath(partOfCheckButtonXPath+"[contains(text(),'"+buttonName+"')]/preceding-sibling::input");
    }



    public void deselectAll(){
        getDriver().findElements(By.xpath(xPathOptionsInputsLocator)).stream()
                   .filter((btn)->(btn.isSelected()))
                   .forEach(btn->btn.findElement(By.xpath(".//following-sibling::label"))
                   .click());
    }


    public void selectAll(){
        getDriver().findElements(By.xpath(xPathOptionsInputsLocator)).stream()
                   .filter((btn)->(!btn.isSelected()))
                   .forEach(btn->btn.findElement(By.xpath(".//following-sibling::label"))
                   .click());
    }

}
