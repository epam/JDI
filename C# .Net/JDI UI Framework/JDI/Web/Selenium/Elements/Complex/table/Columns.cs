using System;
using System.Collections;
using System.Collections.Generic;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Columns : TableLine
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

        public Dictionary<string, ICell> GetColumn(int colNum)
        {
            throw new System.NotImplementedException();
        }

        internal Dictionary<string, ICell> GetColumn(string colName)
        {
            throw new NotImplementedException();
        }

        public List<string> GetColumnValue(int colNum)
        {
            throw new NotImplementedException();
        }

        public List<string> GetColumnValue(string colName)
        {
            throw new NotImplementedException();
        }

        public Dictionary<string, Dictionary<string, ICell>> Get()
        {
            throw new NotImplementedException();
        }
    }
}