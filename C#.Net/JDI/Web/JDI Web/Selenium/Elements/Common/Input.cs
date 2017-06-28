using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Input : TextField
    {
        public Input() : base(null) { }

        public Input(By byLocator) : base(byLocator)
        {

        }
    }
}
