using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Footer:Section
    {
        [FindBy(PartialLinkText = "About")]            
        public Link About;
    }
}
