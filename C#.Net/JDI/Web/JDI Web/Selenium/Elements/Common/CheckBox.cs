using System;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Common
{
    public class CheckBox : Clickable, ICheckBox
    {
        public CheckBox() : this(null) { }
        public CheckBox(By byLocator = null, IWebElement webElement = null, WebBaseElement element = null)
            : base(byLocator, webElement, element:element) { }

        public Action<CheckBox> CheckAction = el =>
        {
            if (!el.IsCheckedAction(el))
                el.ClickAction(el);
            if (!el.IsCheckedAction(el))
                throw Exception("Can't check element. Verify locator for click or isCheckedAction");
        };
        public void Check()
        {
            Actions.Check(el => CheckAction(this));
        }

        protected void UncheckAction(WebBaseElement el)
        {
            if (IsCheckedAction((CheckBox)el))
                ClickAction(el);
            if (IsCheckedAction((CheckBox)el))
                throw Exception("Can't uncheck element. Verify locator for click or isCheckedAction");
        }
        public void Uncheck()
        {
            Actions.Uncheck(UncheckAction);
        }

        public Func<CheckBox, bool> IsCheckedAction =
            el => el.IsSelected(el) || el.IsCheckedByAttribute(el);

        public Func<WebBaseElement, bool> IsCheckedByAttribute =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("checked") != null, false);

        public Func<WebBaseElement, bool> IsSelected =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.Selected, false);

        public bool IsChecked()
        {
            return Actions.IsChecked(el => IsCheckedAction(this));
        }

        protected Action<WebBaseElement, string> SetValueAction = (el, value) =>
        {
            switch (value.ToLower())
            {
                case "true":
                case "1":
                case "check":
                    ((CheckBox) el).Check();
                    break;
                case "false":
                case "0":
                case "uncheck":
                    ((CheckBox) el).Uncheck();
                    break;
                default:
                    throw Exception(
                        $"SetValue not specified correctly {value}, expected: 'true','false','0','1','check','uncheck'");
            }
        };

        public string Value
        {
            get => Actions.GetValue(GetValueFunc);
            set => Actions.SetValue(value, SetValueAction);
        }

        protected Func<WebBaseElement, string> GetValueFunc = el => ((CheckBox) el).IsChecked() + "";

    }
}
