using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using RestSharp.Extensions;
using static JDI_Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.WebActions
{
    public class ElementsActions
    {
        private readonly WebBaseElement _element;

        public ElementsActions(WebBaseElement element)
        {
            _element = element;
        }

        public ActionInvoker Invoker => _element.Invoker;

        // Element Actions
        public bool IsDisplayed(Func<WebBaseElement, bool> isDisplayed)
        {
            return Invoker.DoJActionResult("Is element displayed", isDisplayed);
        }

        public bool IsHidden(Func<WebBaseElement, bool> isHidden)
        {
            return Invoker.DoJActionResult("Is element hidden", isHidden);
        }

        public void WaitDisplayed(Func<WebBaseElement, bool> isDisplayed)
        {
            Invoker.DoJActionResult("Wait element displayed", isDisplayed);
        }

        public void WaitVanished(Func<WebBaseElement, bool> isVanished)
        {
            Invoker.DoJActionResult("Wait element vanished", isVanished);
        }

        // Value Actions
        public string GetValue(Func<WebBaseElement, string> getValueFunc)
        {
            return Invoker.DoJActionResult("Get value", getValueFunc);
        }

        public void SetValue(string value, Action<WebBaseElement, string> setValueAction)
        {
            Invoker.DoJAction("Get value", el => setValueAction(el, value));
        }

        // Click Action
        public void Click(Action<WebBaseElement> clickAction)
        {
            Invoker.DoJAction("Click on Element", clickAction);
        }

        // Text Actions
        public string GetText(Func<WebBaseElement, string> getTextAction)
        {
            return Invoker.DoJActionResult("Get text", getTextAction);
        }

        public string WaitText(string text, Func<WebBaseElement, string> getTextAction)
        {
            Func<WebBaseElement, Func<string>> textAction = el => () => getTextAction(el);
            return Invoker.DoJActionResult($"Wait text contains '{text}'",
                el => textAction(el).GetByCondition(t => t.Contains(text)));
        }

        public string WaitMatchText(string regEx, Func<WebBaseElement, string> getTextAction)
        {
            Func<WebBaseElement, Func<string>> textAction = el => () => getTextAction(el);
            return Invoker.DoJActionResult($"Wait text match regex '{regEx}'",
                el => textAction(el).GetByCondition(t => t.Matches(regEx)));
        }

        // Check/Select Actions
        public bool Selected(Func<WebBaseElement, bool> isSelectedAction)
        {
            return Invoker.DoJActionResult("Is Selected", isSelectedAction);
        }

        public void Check(Action<WebBaseElement> checkAction)
        {
            Invoker.DoJAction("Check Checkbox", checkAction);
        }

        public void Uncheck(Action<WebBaseElement> uncheckAction)
        {
            Invoker.DoJAction("Uncheck Checkbox", uncheckAction);
        }

        public bool IsChecked(Func<WebBaseElement, bool> isCheckedAction)
        {
            return Invoker.DoJActionResult("IsChecked",
                isCheckedAction,
                result => "Checkbox is " + (result ? "checked" : "unchecked"));
        }

        // Input Actions
        public void InputLines(Action<WebBaseElement> clearAction, Action<WebBaseElement, string> inputAction, params string[] textLines)
        {
            Invoker.DoJAction("Input several lines of text in textarea",
                el =>
                {
                    clearAction(el);
                    inputAction(el, string.Join("\n", textLines));
                });
        }

        public void AddNewLine(string textLine, Action<WebBaseElement, string> inputAction)
        {
            Invoker.DoJAction("Add text from new line in textarea",
                el => inputAction(el, "\n" + textLine));
        }

        public string[] GetLines(Func<WebBaseElement, string> getTextAction)
        {
            return Invoker.DoJActionResult("Get text as lines", el => Regex.Split(getTextAction(el), "\\\\n"));
        }

        public void Input(string text, Action<WebBaseElement, string> inputAction)
        {
            Invoker.DoJAction("Input text '" + text + "' in text field",
                el => inputAction(el, text));
        }

        public void Clear(Action<WebBaseElement> clearAction)
        {
            Invoker.DoJAction("Clear text field", clearAction);
        }

        public void Focus(Action<WebBaseElement> focusAction)
        {
            Invoker.DoJAction("Focus on text field", focusAction);
        }

        // Selector
        public void Select(string name, Action<WebBaseElement, string> selectAction)
        {
            Invoker.DoJAction($"Select '{name}'", el => selectAction(el, name));
        }

        public void Select(int index, Action<WebBaseElement, int> selectByIndexAction)
        {
            Invoker.DoJAction($"Select '{index}'", el => selectByIndexAction(el, index));
        }

        public bool Selected(string name, Func<WebBaseElement, string, bool> isSelectedAction)
        {
            return Invoker.DoJActionResult($"Wait is '{name}' selected", el => isSelectedAction(el, name));
        }

        public string Selected(Func<WebBaseElement, string> isSelectedAction)
        {
            return Invoker.DoJActionResult("Get Selected element name", isSelectedAction);
        }

        public int SelectedIndex(Func<WebBaseElement, int> isSelectedAction)
        {
            return Invoker.DoJActionResult("Get Selected element index", isSelectedAction);
        }

        //MultiSelector
        public void Select(Action<WebBaseElement, IList<string>> selectListAction, params string[] names)
        {
            Invoker.DoJAction($"Select '{names.Print()}'", el => selectListAction(el, names));
        }

        public void Select(Action<WebBaseElement, IList<int>> selectListAction, int[] indexes)
        {
            var listIndexes = indexes.Select(i => i.ToString()).ToList();
            Invoker.DoJAction($"Select '{listIndexes.Print()}'", el => selectListAction(el, indexes));
        }

        public List<string> AreSelected(Func<WebBaseElement, IList<string>> getNames, Func<WebBaseElement, string, bool> waitSelectedAction)
        {
            return Invoker.DoJActionResult("Are selected", el =>
                getNames(el).Where(name => waitSelectedAction(el, name)).ToList());
        }

        public void WaitSelected(Func<WebBaseElement, string, bool> waitSelectedAction, params string[] names)
        {
            var result = Invoker.DoJActionResult($"Are deselected '{names.Print()}'", 
                el => names.All(name => waitSelectedAction(el, name)) );
            Asserter.IsTrue(result);
        }

        public List<string> AreDeselected(Func<WebBaseElement, IList<string>> getNames, Func<string, bool> waitSelectedAction)
        {
            return Invoker.DoJActionResult("Are deselected", el =>
                getNames(el).Where(name => !waitSelectedAction.Invoke(name)).ToList());
        }

        public void WaitDeselected(Func<WebBaseElement, string, bool> waitSelectedAction, params string[] names)
        {
            var result = Invoker.DoJActionResult($"Are deselected '{names.Print()}'", 
                el => names.All(name => !waitSelectedAction(el, name)));
            Asserter.IsTrue(result);
        }
    }
}
