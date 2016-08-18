using System;
using System.Collections.Generic;
using System.Linq;
using JDI_Commons;
using Epam.JDI.Core.Attributes;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Web.Selenium.Attributes;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Utils;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class Form : WebElement, IForm
    {
        public By LocatorTemplate;

        protected Action<Form, string, ISetValue> SetFieldValueAction =
            (f, text, element) => element.Value = text;

        protected Func<Form, IHasValue, string> GetFieldValueAction =
            (f, element) => element.Value;

        public void Fill(Dictionary<string, string> map)
        {
            this.GetFields(typeof(ISetValue)).ForEach(element =>
            {
                var fieldValue = map.FirstOrDefault(pair =>
                    GetElementClass.NamesEqual(pair.Key, NameAttribute.GetElementName(element))).Value;
                if (fieldValue == null) return;
                var setValueElement = (ISetValue)element.GetValue(this);
                DoActionRule(fieldValue, val => SetFieldValueAction(this, val, setValueElement));
            });
        }
        public void Submit(Dictionary<string, string> objStrings)
        {
            Fill(objStrings);
            GetElementClass.GetButton("Submit").Click();
        }private void SetText(string text)
        {
            var field = this.GetFields(typeof(ISetValue))[0];
            var setValueElement = (ISetValue)field.GetValue(this);
            DoActionRule(text, val => SetFieldValueAction(this, val, setValueElement));
        }
        public void Submit(string text)
        {
            SetText(text);
            GetElementClass.GetButton("Submit").Click();
        }
        public void Submit(string text, string buttonName)
        {
            SetText(text);
            GetElementClass.GetButton(buttonName).Click();
        }
        public void Login(string text)
        {
            Submit(text, "Login");
        }
        public void Add(string text)
        {
            Submit(text, "Add");
        }
        public void Publish(string text)
        {
            Submit(text, "Publish");
        }
        public void Save(string text)
        {
            Submit(text, "Save");
        }
        public void Update(string text)
        {
            Submit(text, "Update");
        }
        public void Cancel(string text)
        {
            Submit(text, "Cancel");
        }
        public void Close(string text)
        {
            Submit(text, "Close");
        }
        public void Back(string text)
        {
            Submit(text, "Back");
        }
        public void Select(string text)
        {
            Submit(text, "Select");
        }
        public void Next(string text)
        {
            Submit(text, "Next");
        }
        public void Search(string text)
        {
            Submit(text, "Search");
        }

        public IList<string> Verify(Dictionary<string, string> objStrings)
        {
            var compareFalse = new List<string>();
            this.GetFields(typeof(IHasValue)).ForEach(field => {
                var fieldValue = objStrings.FirstOrDefault(pair =>
                    GetElementClass.NamesEqual(pair.Key, NameAttribute.GetElementName(field))).Value;
                if (fieldValue == null) return;
                var valueField = (IHasValue)field.GetValue(this);
                DoActionRule(fieldValue, expected => {
                    var actual = GetFieldValueAction(this, valueField).Trim();
                    if (actual.Equals(expected)) return;
                    compareFalse.Add($"Field '{field.Name}' (Actual: '{actual}' <> Expected: '{expected}')");
                });
            });
            return compareFalse;
        }

        public void Check(Dictionary<string, string> objStrings)
        {
            var result = Verify(objStrings);
            if (result.Count > 0)
                throw Exception("Check form failed:" + result.Print("".FromNewLine()).FromNewLine());
        }
        protected Func<Form, string> GetValueAction =>
            f => this.GetFields(typeof(IHasValue)).Select(field => ((IHasValue)field.GetValue(this)).Value).Print();

        protected Action<Form, string> SetValueAction =>
            (f, value) => Submit(value.ParseAsString());

        public string Value
        {
            get { return Actions.GetValue(f => GetValueAction(this)); }
            set { Actions.SetValue(value, (f, val) => SetValueAction(this, value)); }
        }
    }
    public class Form<T> : Form, IForm<T>
    {

        public void Fill(T entity)
        {
            Fill(entity.ToSetValue());
        }

        public IList<string> Verify(T entity)
        {
            return Verify(entity.ToSetValue());
        }

        public void Search(T entity)
        {
            Submit(entity, "Search");
        }

        public void Submit(T entity, string buttonName)
        {
            Fill(entity.ToSetValue());
            GetElementClass.GetButton(buttonName).Click();
        }

        public void Submit(T entity)
        {
            Submit(entity, "Submit");
        }

        public void Login(T entity)
        {
            Submit(entity, "Login");
        }

        public void Add(T entity)
        {
            Submit(entity, "Add");
        }

        public void Publish(T entity)
        {
            Submit(entity, "Publish");
        }

        public void Save(T entity)
        {
            Submit(entity, "Save");
        }

        public void Update(T entity)
        {
            Submit(entity, "Update");
        }

        public void Cancel(T entity)
        {
            Submit(entity, "Cancel");
        }

        public void Close(T entity)
        {
            Submit(entity, "Close");
        }

        public void Back(T entity)
        {
            Submit(entity, "Back");
        }

        public void Select(T entity)
        {
            Submit(entity, "Select");
        }

        public void Next(T entity)
        {
            Submit(entity, "Next");
        }

        public void Submit(T entity, Enum buttonName)
        {
            Fill(entity.ToSetValue());
            GetElementClass.GetButton(buttonName.ToString().ToLower()).Click();
        }

        public void Check(T entity)
        {
            Check(entity.ToSetValue());
        }
    }
}
