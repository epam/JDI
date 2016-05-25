using System;
using System.Collections.Generic;
using JDI_Core.Attributes.Functions;
using JDI_Core.Interfaces.Complex;
using RestSharp.Extensions;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class PopupForm<T> : Form<T>, IPopup
    {
        public Func<PopupForm<T>, string> GetTextAction = pf => pf.WebElement.Text;
        public new void Submit(Dictionary<string, string> objStrings)
        {
            Fill(objStrings);
            Ok();
        }

        public void Ok()
        {
            GetElementClass.GetButton(Functions.Ok).Click();
        }

        public void Cancel()
        {
            GetElementClass.GetButton(Functions.Cancel).Click();
        }

        public void Close()
        {
            GetElementClass.GetButton(Functions.Close).Click();
        }
        

        public string Text => Invoker.DoJActionResult("Get text", pf => ((PopupForm<T>)pf).GetTextAction(this));

        public string WaitText(string text)
        {
            return Invoker.DoJActionResult($"Wait text contains '{text}'",
                    pf => Timer.GetResultByCondition(() => GetTextAction(this), t => t.Contains(text)));
        }

        public string WaitMatchText(string regEx)
        {
            return Invoker.DoJActionResult($"Wait text match regex '{regEx}'",
                    pf => Timer.GetResultByCondition(() => GetTextAction(this), t => t.Matches(regEx)));
        }
    }
}
