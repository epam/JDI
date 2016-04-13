using System;
using Epam.JDI.Core.Interfaces.Common;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Common
{
    public class TextArea : TextField, ITextArea
    {
        public TextArea() : this (null) { }
        public TextArea(By byLocator = null, IWebElement webElement = null)
                : base(byLocator, webElement)
        { }

        public void InputLines(params string[] textLines)
        {
            Actions.InputLines(ClearAction, InputAction, textLines);
        }

        public void AddNewLine(string textLine)
        {
            Actions.AddNewLine(textLine, InputAction);
        }
        public string[] GetLines()
        {
            return Actions.GetLines(GetTextFunc);
        }
    }
}
