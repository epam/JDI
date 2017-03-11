using Epam.JDI.Core.Interfaces.Common;
using JDI_UIWebTests.Enums;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Summary: Section
    {
        [FindBy(Css = "#odds-selector p")]
        public RadioButtons<Odds> OddNumbers;

        // Exception in CascadeInit
        //[FindBy(Css = "#odds-selector p")]
        //public Selector<Odds> OddNumbersSelector;
                
        [FindBy(Id = "calculate-button")]
        public IButton Calculate;
    
    }
}
