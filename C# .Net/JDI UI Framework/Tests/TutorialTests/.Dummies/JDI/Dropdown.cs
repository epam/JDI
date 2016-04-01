
using System;
using System.Collections.Generic;
using System.Reflection;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Complex;
using OpenQA.Selenium;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    public class Dropdown : IDropDown
    {
        private By by1;
        private By by2;

        public Dropdown() { }

        public Dropdown(By by1, By by2)
        {
            this.by1 = by1;
            this.by2 = by2;
        }

        public IAvatar Avatar
        {
            get
            {
                throw new NotImplementedException();
            }

            set
            {
                throw new NotImplementedException();
            }
        }

        public string Name
        {
            get
            {
                throw new NotImplementedException();
            }

            set
            {
                throw new NotImplementedException();
            }
        }

        public object Parent
        {
            get
            {
                throw new NotImplementedException();
            }

            set
            {
                throw new NotImplementedException();
            }
        }

        public string ParentTypeName
        {
            get
            {
                throw new NotImplementedException();
            }
        }

        public string TypeName
        {
            get
            {
                throw new NotImplementedException();
            }

            set
            {
                throw new NotImplementedException();
            }
        }

        public void Click()
        {
            throw new NotImplementedException();
        }

        public void Close()
        {
            throw new NotImplementedException();
        }

        public void Expand()
        {
            throw new NotImplementedException();
        }

        public string GetAttribute(string name)
        {
            throw new NotImplementedException();
        }

        public List<string> GetNames()
        {
            throw new NotImplementedException();
        }

        public List<string> GetOptions()
        {
            throw new NotImplementedException();
        }

        public string GetOptionsAsText()
        {
            throw new NotImplementedException();
        }

        public string GetSelected()
        {
            throw new NotImplementedException();
        }

        public int GetSelectedIndex()
        {
            throw new NotImplementedException();
        }

        public string GetText()
        {
            throw new NotImplementedException();
        }

        public string GetValue()
        {
            throw new NotImplementedException();
        }

        public List<string> GetValues()
        {
            throw new NotImplementedException();
        }

        public bool IsDisplayed()
        {
            throw new NotImplementedException();
        }

        public bool IsHidden()
        {
            throw new NotImplementedException();
        }

        public bool IsSelected(IConvertible name)
        {
            throw new NotImplementedException();
        }

        public bool IsSelected(string name)
        {
            throw new NotImplementedException();
        }

        public void Select(int index)
        {
            throw new NotImplementedException();
        }

        public void Select(IConvertible name)
        {
            throw new NotImplementedException();
        }

        public void Select(string name)
        {
            throw new NotImplementedException();
        }

        public void SetAttribute(string attributeName, string value)
        {
            throw new NotImplementedException();
        }

        public void SetFunction(Functions function)
        {
            throw new NotImplementedException();
        }

        public void SetName(FieldInfo field)
        {
            throw new NotImplementedException();
        }

        public void SetValue(string value)
        {
            throw new NotImplementedException();
        }

        public void WaitAttribute(string name, string value)
        {
            throw new NotImplementedException();
        }

        public void WaitDisplayed()
        {
            throw new NotImplementedException();
        }

        public string WaitMatchText(string regEx)
        {
            throw new NotImplementedException();
        }

        public void WaitSelected(IConvertible name)
        {
            throw new NotImplementedException();
        }

        public void WaitSelected(string name)
        {
            throw new NotImplementedException();
        }

        public string WaitText(string text)
        {
            throw new NotImplementedException();
        }

        public void WaitVanished()
        {
            throw new NotImplementedException();
        }
    }
}
