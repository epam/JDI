using System;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    public class Menu<TEnum> : Epam.JDI.Web.Selenium.Elements.Complex.Menu
    {
        public Action<string> SelectAction;
    }
}
