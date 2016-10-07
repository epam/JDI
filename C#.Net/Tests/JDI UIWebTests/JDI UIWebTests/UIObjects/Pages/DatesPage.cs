using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;
using JDIWebTests.UIObjects.Sections;

namespace JDI_UIWebTests.UIObjects.Pages
{
    public class DatesPage:WebPage
    {
        [FindBy(Css = "#datepicker input")]
        public DatePicker Datepicker;

        [FindBy(Css = "[data-provides=fileinput]")]
        public IFileInput ImageInput;

        [FindBy(Css = "")]
        public ILabel UploadedFileName;

        [FindBy(Css = "main form")]
        public ContactForm ContactForm;
    }
}
