using System;
using System.Linq;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using JDI_Web.Settings;
using OpenQA.Selenium;
using static System.String;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Complex.Table
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
            GetValueFunc = w => TextAction(this);
        }
        
        public string ColumnName => !IsNullOrEmpty(_columnName)
                    ? _columnName
                    : Table.Columns.Headers[ColumnNum - 1];

        public string RowName => !IsNullOrEmpty(_rowName)
                    ? _rowName
                    : Table.Rows.Headers[RowNum - 1];
        protected Func<Cell, string> TextAction => c => Get().GetText;
        
        protected new Func<Cell, bool> SelectedAction => c => Get().Selected;

        public SelectableElement Get()
        {
            return WebElement != null
                    ? new SelectableElement(webElement: WebElement)
                    : new SelectableElement(_cellLocatorTemplate.FillByTemplate(ColumnIndex, RowIndex));
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
            cell.WebAvatar.ByLocator = locator.FillByTemplate(RowIndex, ColumnIndex);
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
