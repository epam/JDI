using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Header:Section
    {
        [FindBy(XPath = "//img[@src=\"label/Logo_Epam_Color.svg\"]")]
        public IImage Image;

        public JdiSearch Search;
    }
}
