using Epam.JDI.Core.Interfaces.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Web.Selenium.Elements.Complex.Table
{
    public class FilterDsl
    {
        public static string TextOf(IText textElement)
        {
            return textElement.GetText;
        }
    }
}
