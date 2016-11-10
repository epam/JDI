using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_UIWebTests.Enums;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Summary: Section
    {
        /*
        [FindBy(Css = "#odds-selector p")]
        public ISelector Odds;
        
        [FindBy(Css = "#odds-selector p")]
        public RadioButtons<Odds> OddsR;
        */        
        [FindBy(Css = "#even-selector p")]
        public Selector SimpleSelector;
        
        [FindBy(Id = "calculate-button")]
        public IButton Calculate;
    
    }
}
