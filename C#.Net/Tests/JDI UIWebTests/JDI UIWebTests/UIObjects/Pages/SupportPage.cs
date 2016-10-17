using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Pages
{
    public class SupportPage : WebPage
    {
        [FindBy(Css = ".uui-table")]
        public ITable SupportTable;
    }
}
