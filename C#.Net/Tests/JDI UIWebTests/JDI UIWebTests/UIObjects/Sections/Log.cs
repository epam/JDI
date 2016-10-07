using JDI_Web.Selenium.Elements.Complex;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class Log:TextList
    {
        [FindBy(Css = ".logs li")]
        public TextList LogList;
    }
}
