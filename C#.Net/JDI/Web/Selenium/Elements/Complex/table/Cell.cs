using System;
using System.Linq;
using Epam.JDI.Commons;
using Epam.JDI.Core;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using Epam.JDI.Web.Settings;
using OpenQA.Selenium;
using static System.String;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Cell : SelectableElement, ICell
    {
        public int RowIndex { set; get; }
        public int ColumnIndex { set; get; }
        public Table Table { get; set; }
        public int ColumnNum { get; set; }
        public int RowNum { get; set; }
        private string _columnName { get; set; }
        private string _rowName { get; set; }
        private readonly By _cellLocatorTemplate = By.XPath(".//tr[{1}]/td[{0}]");
        

        public Cell(int columnNum, int rowNum, string colName, string rowName,
                    By cellLocatorTemplate, Table table, int columnIndex = -1, int rowIndex = -1, IWebElement webElement = null)
        {
            if (columnIndex > 0)
                ColumnIndex = table.Rows.HasHeader && table.Rows.LineTemplate == null 
                    ? ColumnIndex + 1 
                    : ColumnIndex;
            WebElement = webElement;
            RowIndex = rowIndex;
            ColumnNum = columnNum;
            RowNum = rowNum;
            _columnName = colName;
            _rowName = rowName;
            if (cellLocatorTemplate != null)
                _cellLocatorTemplate = cellLocatorTemplate;
            Table = table;
            ClickAction = c => ((Cell)c).Get().Click();
    }
        
        public string ColumnName => _columnName != null && !_columnName.Equals("")
                    ? _columnName
                    : Table.Columns.Headers[ColumnNum - 1];

        public string RowName => _rowName != null && !_rowName.Equals("")
                    ? _rowName
                    : Table.Rows.Headers[RowNum - 1];
        protected Func<Cell, string> TextAction => c => Get().Text;


        protected new Func<Cell, bool> SelectedAction => c => Get().Selected;

        public SelectableElement Get()
        {
            return WebElement != null
                    ? new SelectableElement(webElement: WebElement)
                    : new SelectableElement(_cellLocatorTemplate.FillByMsgTemplate(ColumnIndex, RowIndex));
        }

        public T Get<T>(Type clazz) where T : WebBaseElement
        {
            T instance;
            try
            {
                instance = (T) Activator.CreateInstance(clazz.IsInterface
                        ? MapInterfaceToElement.ClassFromInterface(clazz)
                        : clazz);
            }
            catch
            {
                throw Exception("Can't get Cell from interface/class: " + clazz.ToString().Split("\\.").Last());
            }
            return Get(instance);
        }

        public T Get<T>(T cell) where T : WebBaseElement
        {
            var locator = cell.Locator;
            if (locator == null || locator.ToString().Equals(""))
                locator = _cellLocatorTemplate;
            if (!locator.ToString().Contains("{0}") || !locator.ToString().Contains("{1}"))
                throw Exception("Can't create cell with locator template " + cell.Locator
                        + ". Template for Cell should contains '{0}' - for column and '{1}' - for row indexes.");
            cell.WebAvatar.ByLocator = locator.FillByMsgTemplate(RowIndex, ColumnIndex);
            cell.Parent = Table;
            return cell;
        }

        public Cell UpdateData(string colName, string rowName)
        {
            if (IsNullOrEmpty(_columnName) && !IsNullOrEmpty(colName))
                _columnName = colName;
            if (IsNullOrEmpty(_rowName) && !IsNullOrEmpty(rowName))
                _rowName = rowName;
            return this;
        }
    }
}
