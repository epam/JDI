using Epam.JDI.Core.Interfaces.Complex;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDIWebTests.UIObjects.Sections
{
    public class SummaryForm : Form
    {
        [FindBy(Css = "section.horizontal-group:first-child .radio")]
        public IRadioButtons Section1;

        [FindBy(Css = "section.horizontal-group:nth-child(2) .radio")]
        public IRadioButtons Section2;
    }
}
