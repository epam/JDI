using Epam.JDI.Core.Interfaces.Complex;
using System;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public class Menu : Menu<IConvertible>, IMenu { }
    public class Menu<TEnum> : Selector<TEnum>, IMenu<TEnum>
        where TEnum : IConvertible
    {

    }
}
