package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.ICheckList;
import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.core.interfaces.complex.IDropList;
import com.epam.jdi.uitests.testing.unittests.custom.CheckListOfTypeOne;
import com.epam.jdi.uitests.testing.unittests.enums.ColorsList;
import com.epam.jdi.uitests.testing.unittests.enums.Metals;
import com.epam.jdi.uitests.testing.unittests.enums.Nature;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.Summary;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import com.epam.jdi.uitests.web.selenium.elements.complex.ComboBox;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JComboBox;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropList;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class MetalsColorsPage extends WebPage {

    @FindBy(id = "summary-block")
    public Summary summary;

    @FindBy(id = "calculate-button")
    public Label calculate;

    @FindBy(id = "calculate-button")
    public Button calculateButton;
    @FindBy(id = "calculate-button")
    public ILabel calculateLabel;


    @JDropdown(
            jroot = @JFindBy(css = ".colors"),
            jexpand = @JFindBy(css = ".caret"),
            jlist = @JFindBy(tagName = "li"),
            jvalue = @JFindBy(css = ".filter-option")
    ) public IDropDown colorsRootExpandListValue;

    @JDropdown(
            jroot = @JFindBy(css = ".colors"),
            jexpand = @JFindBy(css = ".caret")
    ) public IDropDown colorsRootExpand;

    @JDropdown(
            jroot = @JFindBy(css = ".colors")
    ) public IDropDown colorsRoot;

    @JDropdown(
            jroot = @JFindBy(css = ".colors"),
            jvalue = @JFindBy(css = ".filter-option")
    ) public IDropDown colorsRootValue;

    @JDropdown(
            jroot = @JFindBy(css = ".colors"),
            jlist = @JFindBy(tagName = "li")
    ) public IDropDown colorsRootList;

    @JDropdown(
            jroot = @JFindBy(css = ".colors"),
            jlist = @JFindBy(tagName = "li"),
            jvalue = @JFindBy(css = ".filter-option")
    ) public IDropDown colorsRootListValue;


    @JDropList(
            jroot = @JFindBy(xpath = ".salad"),
            jlist = @JFindBy(tagName = "li")
    //        jvalue = @JFindBy(tagName = "button")
    ) public IDropList saladDL;

    @JFindBy(id = "salad-dropdown")
    public IButton button;


    @JComboBox(
            root = @JFindBy(css = ".colors"),
            list = @JFindBy(tagName = "li"),
            value = @JFindBy(css = ".filter-option"),
            expand = @JFindBy(css = ".caret")
    ) public IComboBox jComboBoxRootListValueExpand;

    @JComboBox(
            root = @JFindBy(css = ".colors"),
            list = @JFindBy(tagName = "li"),
            value = @JFindBy(css = ".filter-option")
    ) public IComboBox jComboBoxRootListValue;

    @JComboBox(
            root = @JFindBy(css = ".colors"),
            value = @JFindBy(css = ".filter-option")
    ) public IComboBox jComboBoxRootList;

    @JDropdown(
        jroot = @JFindBy(css = ".colors"),
        jlist = @JFindBy(tagName = "li"),
        jvalue = @JFindBy(css = ".filter-option")
    )
    public IDropDown<ColorsList> colors;


    @FindBy(css = ".summ-res")
    public IText calculateText = new Text(){
        @Override
        protected String getTextAction() {
             return getDriver().findElement(By.cssSelector(".summ-res")).getText();
        }
    };


    public Text c1alculateText;

    @FindBy(css = "#elements-checklist p")
    public ICheckList<Nature> nature = new CheckList<Nature>() {
        @Override
        protected boolean isSelectedAction(WebElement el) {
            return el.findElement(By.tagName("input")).getAttribute("checked") != null;
        }
    };

    public CheckListOfTypeOne natureExtended = new CheckListOfTypeOne("//section[@id='elements-checklist']/p[@class='checkbox']", "/label", "/input");

    @FindBy(xpath = "//*[@id='elements-checklist']//*[label[text()='%s']]/label")
    public ICheckList<Nature> natureTemplate;

    @FindBy(xpath = "//*[@id='elements-checklist']//*[text()='Water']")
    public CheckBox cbWater = new CheckBox() {
        @Override
        protected boolean isCheckedAction() {
            return new Element(By.xpath("//*[@id='elements-checklist']//*[*[text()='Water']]/input"))
                    .getInvisibleElement().getAttribute("checked") != null;
        }
    };

    public IComboBox<Metals> comboBox =
            new ComboBox<Metals>(By.cssSelector(".metals .caret"), By.cssSelector(".metals li span"), By.cssSelector(".metals input")) {
                @Override
                protected String getTextAction() {
                    return new Text(By.cssSelector(".metals .filter-option")).getText();
                }
            };
}


