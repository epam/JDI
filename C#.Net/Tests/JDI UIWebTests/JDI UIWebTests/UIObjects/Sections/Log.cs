using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Attributes;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Log:TextList
    {
        [FindBy(Css = ".logs li")]
        public TextList LogList;
    }
}
