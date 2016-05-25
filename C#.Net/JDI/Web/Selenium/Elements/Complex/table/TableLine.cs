using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Epam.JDI.Core;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public enum ElementIndexType { Nums, Names }
    public abstract class TableLine : WebBaseElement, ITableLine
    {
        public bool HasHeader { set; get; }
        public Table Table { get; set; }

        private int _count;

        public int Count
        {
            get { return GetCount(false); }
            set { if (Table.Cache) _count = value; }
        }
        
        private IList<string> _headers;

        public void AddHeaders(IList<string> headers)
        {
            _headers = new List<string>(headers);
        }

        public IList<string> Headers
        {
            set
            {
                if(Table.Cache)
                    _headers = new List<string>(value);
            }
            get
            {
                if (_headers != null)
                    return new List<string>(_headers);
                var localHeaders = HasHeader ? Timer.GetResult(GetHeadersTextAction) : GetNumList(Count);
                if (localHeaders == null || localHeaders.Count == 0)
                    return new List<string>();
                if (Count > 0 && localHeaders.Count > Count)
                {
                    var temp = new List<string>();
                    for (var i = 0; i < Count; i++)
                        temp.Add(localHeaders[i]);
                    localHeaders = temp;
                }
                Headers = localHeaders;
                Count = localHeaders.Count;
                return localHeaders;
            }
        }

        public abstract Dictionary<string, Dictionary<string, ICell>> Get();

        public Dictionary<string, Dictionary<string, string>> AsText {
            get
            {
                return Get().ToDictionary(line => line.Key, line 
                    => line.Value.ToDictionary(el => el.Key, el => el.Value.Text));
            }
        }
        public Dictionary<string, SelectableElement> Header()
        {
            return GetHeadersAction(this).ToDictionary(key => key.Text, value => new SelectableElement());
        }

        public SelectableElement Header(string name)
        {
            return Header()[name];
        }

        private IList<string> GetNumList(int count)
        {
            return GetNumList(count, 1);
        }

        private IList<string> GetNumList(int count, int from)
        {
            var result = new List<string>();
            for (var i = from; i < count + from; i++)
               result.Add(i.ToString());
            return result;
        }
        public int StartIndex { set; get; } = 1;
        public By HeadersLocator { set; get; }
        protected By DefaultTemplate { set; get; }
        public By LineTemplate { set; get; }
        public ElementIndexType ElementIndex { set; get; }
        public T Clone<T>(T newLine, Table newTable) where T : TableLine
        {
            newLine.HasHeader = HasHeader;
            newLine.ElementIndex = ElementIndex;
            newLine.Table = newTable;
            newLine.Count = Count;
            newLine.Headers = Headers;
            newLine.StartIndex = StartIndex;
            newLine.HeadersLocator = HeadersLocator;
            newLine.DefaultTemplate = DefaultTemplate;
            newLine.LineTemplate = LineTemplate;
            return newLine;
        }

        public IList<IWebElement> GetLineAction(int colNum)
        {
            return Table.GetWebElement().FindElements((LineTemplate ?? DefaultTemplate).FillByTemplate(colNum)).ToList();
        }
        protected IList<IWebElement> GetLineAction(string lineName)
        {
            if (LineTemplate != null && LineTemplate.GetByLocator().Contains("{0}"))
                return Table.WebElement.FindElements(LineTemplate.FillByTemplate(lineName));
            var index = Headers.IndexOf(lineName) + 1; 
            return LineTemplate == null 
                ? GetLineAction(index) 
                : Table.GetWebElement().FindElements(LineTemplate.FillByTemplate(index));
        }

        protected abstract IList<IWebElement> GetFirstLine();

        public int GetCount(bool acceptEmpty)
        {
            if (_count > 0)
                return _count;
            if (_headers != null && _headers.Count > 0)
                return _headers.Count;
            var elements = acceptEmpty 
                ? GetFirstLine()
                : Timer.GetResultByCondition(GetFirstLine, el => el != null && el.Count > 0);
            return elements?.Count ?? 0;
        }
        public void Clean()
        {
            Headers = null;
            Count = 0;
        }

        protected IList<string> GetHeadersTextAction()
        {
            return GetHeadersAction(this).Select(el => el.Text).ToList();
        }

        public void Update(TableLine tableLine)
        {
            if (tableLine.Count > 0)
                Count = tableLine.Count;
            if (tableLine.StartIndex != 1)
                StartIndex = tableLine.StartIndex;
            if (tableLine.Headers != null && tableLine.Headers.Count > 0)
                Headers = tableLine.Headers;
            if ((tableLine.GetType().IsAssignableFrom(typeof(Columns)) && !tableLine.HasHeader) ||
                (tableLine.GetType().IsAssignableFrom(typeof(Row)) && tableLine.HasHeader))
                    HasHeader = tableLine.HasHeader;

            if (tableLine.ElementIndex != ElementIndexType.Nums)
                ElementIndex = tableLine.ElementIndex;
        }

        protected abstract Func<TableLine, IList<IWebElement>> GetHeadersAction { get; }
    }
}