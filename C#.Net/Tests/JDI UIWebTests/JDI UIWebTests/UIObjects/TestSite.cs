using JDI_UIWebTests.UIObjects.Pages;
using JDI_UIWebTests.UIObjects.Sections;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;
using JDIWebTests.UIObjects.Sections;


namespace JDI_UIWebTests.UIObjects
{
    [Site(Domain = "https://jdi-framework.github.io/tests")]
    public class TestSite:WebSite
    {
        [Page(Url = "/index.htm", Title = "Index Page")]
        public static HomePage HomePage;

        [Page(Url = "/page1.htm", Title = "Contact Form")]    
        public static ContactPage ContactFormPage;

        [Page(Url = "/page2.htm", Title = "Metal and Colors")]
        public static MetalsColorsPage MetalsColorsPage;

        [Page(Url = "/page3.htm", Title = "Support")]        
        public static SupportPage SupportPage;

        [Page(Url = "/page4.htm", Title = "Dates")]
        public static DatesPage Dates;

        [Page(Url = "page6.htm", Title = "Simple Table")]
        public static SimpleTablePage SimpleTablePage;

        [FindBy(Css = ".uui-profile-menu")]
        public static Login Login;

        [FindBy(Css = ".uui-header")]
        public static Header Header;

        [FindBy(Css = ".footer-content")]
        public static Footer Footer;

        [FindBy(Css = "form.form-horizontal")]
        public static LoginForm LoginForm;

        [FindBy(Css = ".logs li")]
        public static TextList ActionsLog;
    }
}
