using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Rows : TableLine
    {
        protected override List<IWebElement> FirstLine { get; }
        protected override int GetCount()
        {
            throw new System.NotImplementedException();
        }

        protected override List<IWebElement> GetHeadersAction()
        {
            throw new System.NotImplementedException();
        }

        public Dictionary<string, Dictionary<string, ICell>> Get()
        {
            throw new System.NotImplementedException();
        }

        public Dictionary<string, ICell> GetRow(int rowNum)
        {
            throw new NotImplementedException();
        }

        internal Dictionary<string, ICell> GetRow(string rowName)
        {
            throw new NotImplementedException();
        }

        public List<string> GetRowValue(int rowNum)
        {
            throw new NotImplementedException();
        }

        internal List<string> GetRowValue(string rowName)
        {
            throw new NotImplementedException();
        }
    }
}