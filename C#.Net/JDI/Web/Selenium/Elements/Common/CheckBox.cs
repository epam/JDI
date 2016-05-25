using System;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Core;
using Epam.JDI.Web.Selenium.Base;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Elements.Common
{
    public class CheckBox : Clickable, ICheckBox
    {
        protected void CheckAction(WebBaseElement el)
        {
            if (!IsCheckedAction(el))
                ClickAction(el);
            if (!IsCheckedAction(el))
                throw Exception("Can't check element. Verify locator for click or isCheckedAction");
        }
        public void Check()
        {
            Actions.Check(CheckAction);
        }

        protected void UncheckAction(WebBaseElement el)
        {
            if (IsCheckedAction(el))
                ClickAction(el);
            if (IsCheckedAction(el))
                throw Exception("Can't uncheck element. Verify locator for click or isCheckedAction");
        }
        public void Uncheck()
        {
            Actions.Uncheck(UncheckAction);
        }

        protected bool IsCheckedAction(WebBaseElement el)
        {
            return ((CheckBox)el).IsCheckedFunc(el);
        }

        private Func<WebBaseElement, bool> IsCheckedFunc =
            el =>
            {
                var checkbox = (CheckBox)el;
                if (checkbox.IsSelected.Invoke(el))
                    el.WebAvatar.FindImmediately(() => checkbox.IsSelected(el), true);
                if(checkbox.IsCheckedByAttribute.Invoke(el))
                    el.WebAvatar.FindImmediately(() => checkbox.IsCheckedByAttribute(el), true);
                return false;
            };


        private Func<WebBaseElement, bool> IsCheckedByAttribute =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("checked") != null, false);

        private Func<WebBaseElement, bool> IsSelected =
            el => el.WebAvatar.FindImmediately(() => el.WebElement.Selected, false);

        public bool IsChecked()
        {
            return Actions.IsChecked(IsCheckedAction);
        }
        protected void SetValueAction(WebBaseElement el, string value)
        {
            switch (value.ToLower())
            {
                case "true":
                case "1":
                case "check":
                    ((CheckBox)el).Check();
                    break;
                case "false":
                case "0":
                case "uncheck":
                    ((CheckBox)el).Uncheck();
                    break;
                default:
                    throw Exception($"SetValue not specified correctly {value}, expected: 'true','false','0','1','check','uncheck'");
            }
        }

        public string Value
        {
            get { return Actions.GetValue(GetValueFunc); }
            set { Actions.SetValue(value, SetValueAction); }
        }

        protected Func<WebBaseElement, string> GetValueFunc = el => ((CheckBox) el).IsChecked() + "";

    }
}
