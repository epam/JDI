using System.Collections.Generic;
using System.Linq;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Elements.Complex.Table
{
    public enum ElementIndexType { Nums, Names }
    public abstract class TableLine : WebBaseElement, ITableLine
    {
        public bool HasHeader { set; get; }
        public Table Table { get; set; }

        private int _count;

        public int Count
        {
            get => GetCount(false);
            set { if (Table.Cache)
                    _count = value; }
        }
        
        private IList<string> _headers;

        public void AddHeaders(IList<string> headers)
        {
            _headers = headers;
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
                    return _headers;
                var localHeaders = HasHeader 
                    ? Timer.GetResult(() => AllHeaders) 
                    : GetNumList(Count);
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
                    => line.Value.ToDictionary(el => el.Key, el => el.Value.GetText));
            }
        }
        public Dictionary<string, SelectableElement> Header()
        {
            return GetHeadersAction.ToDictionary(key => key.Text, 
                value => new SelectableElement(webElement: value));
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
            return GetElementsByTemplate(colNum + StartIndex - 1);
        }
        protected IList<IWebElement> GetLineAction(string lineName)
        {
            if (LineTemplate != null && LineTemplate.GetByLocator().Contains("{0}"))
                return GetElementsByTemplate(lineName);
            var num = Headers.IndexOf(lineName) + 1; 
            return LineTemplate == null 
                ? GetLineAction(num) 
                : GetElementsByTemplate(num);
        }

        private IList<IWebElement> GetElementsByTemplate(object value)
        {
            var locator = (LineTemplate ?? DefaultTemplate).FillByTemplate(value);
            return Table.WebElement.FindElements(locator).Where(el => el.Displayed).ToList();
        }

        protected abstract IList<IWebElement> GetFirstLine { get; }

        public int GetCount(bool acceptEmpty)
        {
            if (_count > 0)
                return _count;
            if (_headers != null && _headers.Count > 0)
                return _headers.Count;

            var elements = GetHeadersAction;
            if (elements.Count == 0)
                elements = GetFirstLine;
            if (!acceptEmpty)
            {
                var tempElements = elements;
                elements = Timer.GetResultByCondition(() => tempElements, el => el != null && el.Count > 0);
            }
            return elements?.Count(el => el.Displayed) ?? 0;
        }
        public void Clean()
        {
            Headers = null;
            Count = 0;
        }

        public IList<string> AllHeaders => GetHeadersAction.Where(el => el.Displayed)
            .Select(el => el.Text).ToList();
        
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

        protected IList<IWebElement> GetHeadersAction 
            => Table.WebElement.FindElements(HeadersLocator);
    }
}