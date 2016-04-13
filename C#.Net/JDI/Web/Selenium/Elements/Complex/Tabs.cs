using System;
using Epam.JDI.Core.Interfaces.Complex;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public class Tabs : Tabs<IConvertible>, ITabs { }
    public class Tabs<TEnum> : Selector<TEnum>, ITabs<TEnum> 
        where TEnum : IConvertible
    {
    }
}
