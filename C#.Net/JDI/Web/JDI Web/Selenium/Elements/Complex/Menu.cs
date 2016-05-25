using System;
using JDI_Core.Interfaces.Complex;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class Menu : Menu<IConvertible>, IMenu { }
    public class Menu<TEnum> : Selector<TEnum>, IMenu<TEnum>
        where TEnum : IConvertible
    {

    }
}
