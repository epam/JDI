using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_UIWebTests.Enums;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using JDIWebTests.UIObjects.Sections;
using OpenQA.Selenium;


namespace JDI_UIWebTests.UIObjects.Pages
{
    public class MetalsColorsPage : WebPage
    {
        [FindBy(Id = "summary-block")]
        public SummaryForm Summary;

        [FindBy(Id = "calculate-button")]
        public Label Calculate;

        [FindBy(Id = "calculate-button")]
        public Button CalculateButton;

        [FindBy(Id = "calculate-button")]
        public ILabel calculateLabel;
        
        public IDropDown<Colors> colors = new Dropdown<Colors>(By.CssSelector(".colors .filter-option"), By.CssSelector(".colors li span"));
             
        [FindBy(Css = ".summ-res")]
        public IText CalculateText;
        
        [FindBy(Css = "#elements-checklist label")]
        public CheckList<Elements> Elements;

        /*
        [FindBy(XPath = "//*[@id='elements-checklist']//*[text()='Water']")]
        public CheckBox CbWater;
        */

        [FindBy(XPath = "//*[@id='elements-checklist']//*[text()='Water']")]
        public CheckBox CbWater = new CheckBox {
            IsCheckedAction = el =>
            {
                return new WebElement(By.XPath("//*[@id='elements-checklist']//*[*[text()='Water']]/input"))
                .GetInvisibleElement().Selected;
                //.GetAttribute("checked") != null;
            }
        };      

        public IComboBox<Metals> comboBox =
            new ComboBox<Metals>(By.CssSelector(".metals .caret"), By.CssSelector(".metals li span"), By.CssSelector(".metals input")) {
                GetTextAction = c => {
                    return new Text(By.CssSelector(".metals .filter-option")).GetText;
                }
        };
    }
}
