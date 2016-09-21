using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Common
{
    public class TextArea : TextField, ITextArea
    {
        public TextArea() : this(null) { }
        public TextArea(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null)
            : base(byLocator, webElement, element:element) { }

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
