using System;
using JDI_Core.Interfaces.Complex;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Complex
{
    public class CheckList : CheckList<IConvertible>, ICheckList
    {
        public CheckList() { }
        public CheckList(By optionsNamesLocator, By allOptionsNamesLocator) 
            : base(optionsNamesLocator, allOptionsNamesLocator) { }
    }
    public class CheckList<TEnum> : MultiSelector<TEnum>, ICheckList<TEnum>
        where TEnum : IConvertible
    {
        public CheckList() { }
        public CheckList(By optionsNamesLocator, By allOptionsNamesLocator) : base(optionsNamesLocator, allOptionsNamesLocator) { }
    }
}
