using Epam.JDI.Core.Interfaces.Complex;
using System;

namespace Epam.JDI.Web.Selenium.Elements.Complex
{
    public class RadioButtons : RadioButtons<IConvertible>, IRadioButtons { }
    public class RadioButtons<TEnum> : Selector<TEnum>, IRadioButtons<TEnum>
        where TEnum : IConvertible
    {
    }
}
