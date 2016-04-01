using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public enum ElementIndexType { Nums, Names }
    public abstract class TableLine : WebElement, ITableLine /*,ICloneable*/
    {
        public bool HasHeader { set; get; }
        //public ElementIndexType elementIndex;
        public Table Table { get; set; }

        private int _count = 0;

        public int Count
        {
            get
            {
                if (_count > 0)
                    return _count;
                if (Headers != null && Headers.Count > 0)
                    return Headers.Count;
                return GetCount();
            }
            set
            {
                if (Table.Cache) _count = value;
            }
        }

        private List<String> _headers;

        public List<String> Headers
        {
            set
            {
                if(Table.Cache)
                    _headers = new List<string>(value);
            }
            get
            {
                if (_headers != null)
                    return new List<String>(_headers);
                var localHeaders = HasHeader ? Timer.GetResult(GetHeadersTextAction) : GetNumList(Count);
                if (localHeaders == null || localHeaders.Count == 0)
                    return new List<string>();
                if (Count > 0 && localHeaders.Count > Count)
                    localHeaders = localHeaders.GetRange(0, Count);
                Headers = localHeaders;
                Count = localHeaders.Count;
                return localHeaders;
            }
        }

        private List<String> GetNumList(int count)
        {
            return GetNumList(count, 1);
        }

        private List<string> GetNumList(int count, int from)
        {
            List<String> result = new List<string>();
            for (int i = from; i < count + from; i++)
               result.Add(i.ToString());
            return result;
        }
        public int StartIndex { set; get; } = 1;
        public By HeadersLocator { set; get; }
        protected By DefaultTemplate { set; get; }
        public By LineTemplate { set; get; }
        public ElementIndexType ElementIndex { set; get; }
        public T Clone<T>(T newTableLine, Table newTable) where T : TableLine
        {
            //asserter.silent(()->super.clone()); //TODO
            newTableLine.HasHeader = HasHeader;
            newTableLine.ElementIndex = ElementIndex;
            newTableLine.Table = newTable;
            newTableLine.Count = Count;
            newTableLine.Headers = Headers;
            newTableLine.StartIndex = StartIndex;
            newTableLine.HeadersLocator = HeadersLocator;
            newTableLine.DefaultTemplate = DefaultTemplate;
            newTableLine.LineTemplate = LineTemplate;
            return newTableLine;
        }

        protected ReadOnlyCollection<IWebElement> GetLineAction(int colNum)
        {
            return Table.GetWebElement().FindElements((LineTemplate ?? DefaultTemplate).FillByTemplate(colNum));
        }
        protected ReadOnlyCollection<IWebElement> GetLineAction(string lineName)
        {
            if (LineTemplate != null && LineTemplate.GetByLocator().Contains("%s"))
                return Table.GetWebElement().FindElements(LineTemplate.FillByTemplate(lineName));
            int index = 0;//GetIndex(Headers(), lineName) + 1; TODO
            return LineTemplate == null ? GetLineAction(index) : Table.GetWebElement().FindElements(LineTemplate.FillByTemplate(index));
        }
        protected abstract List<IWebElement> FirstLine { get; }
        protected abstract int GetCount();
        public void Clean()
        {
            Headers = null;
            Count = 0;
        }

        protected List<String> GetHeadersTextAction()
        {
            //return select(getHeadersAction(), WebElement::getText); TODO
            throw new NotImplementedException();
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
            {
                HasHeader = tableLine.HasHeader;
            }

            if (tableLine.ElementIndex != ElementIndexType.Nums)
                ElementIndex = tableLine.ElementIndex;
        }

        protected abstract List<IWebElement> GetHeadersAction();

        public ReadOnlyDictionary<string, OptionElement> Header()
        {
            //return new Dictionary<string, OptionElement>(GetHeadersAction(), null); // TODO
            throw new NotImplementedException();
        }

        public OptionElement Header(String name)
        {
            return Header()[name];
        }
    }
}