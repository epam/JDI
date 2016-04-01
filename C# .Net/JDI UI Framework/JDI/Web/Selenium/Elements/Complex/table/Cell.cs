using System;
using Epam.JDI.Commons;
using Epam.JDI.Core;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using Epam.JDI.Web.Settings;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    class Cell : OptionElement, ICell
    {
        public string Value { get; set; }
        public int ColumnIndex { get; }
        public int RowIndex { get; }
        public int ColumnNum { get; set; }
        public int RowNum
        {
            get
            {
                throw new NotImplementedException();
            }
            set { _rowNum = value; }
        }
        public string ColumnName { get; set; }
        public string RowName { get; set; }
        public By CellLocatorTemplate { get; } = By.XPath(".//tr[{1}]/td[{0}]"); // TODO
        public Table Table { get; }
        public new IWebElement WebElement { get; set; } // TODO
        private int _rowNum;

        public Cell(IWebElement webElement, int columnNum, int rowNum, string columnName, string rowName, By cellLocatorTemplate, Table table)
        {
            WebElement = webElement;
            ColumnNum = columnNum;
            RowNum = rowNum;
            ColumnName = columnName;
            RowName = rowName;
            if (CellLocatorTemplate != null)
                CellLocatorTemplate = cellLocatorTemplate;
            Table = table;
        }

        public Cell(int columnIndex, int rowIndex, int columnNum, int rowNum, string columnName, string rowName, By cellLocatorTemplate, Table table)
        {
            ColumnIndex = columnIndex;
            RowIndex = rowIndex;
            ColumnNum = columnNum;
            RowNum = rowNum;
            ColumnName = columnName;
            RowName = rowName;
            if (CellLocatorTemplate != null)
                CellLocatorTemplate = cellLocatorTemplate;
            Table = table;
        }

        protected new string GetTextAction()
        {
            return Get().Text;
        }
        protected new void ClickAction()
        {
            Get().Click();
        }
        protected new bool IsSelectedAction() // TODO
        {
            return Get().Selected;
        }

        public OptionElement Get()
        {
            return WebElement != null
                    ? new OptionElement(webElement: WebElement)
                    : new OptionElement(CellLocatorTemplate.FillByTemplate(ColumnIndex, RowIndex));
        }

        public T Get<T>(Type clazz) where T : WebBaseElement
        {
            var instance = ExceptionUtils.ActionWithException(() => (T) (clazz.IsInterface
                ? (T) Activator.CreateInstance(MapInterfaceToElement.ClassFromInterface(clazz))
                : Activator.CreateInstance(clazz)), ex => $"Can't get Cell from interface/class: {clazz.Name} .");
            return Get(instance);
        }

        private T Get<T>(T cell) where T : WebBaseElement
        {
            By locator = null; //cell.Locator; TODO Locator missong
            if (locator == null || locator.ToString().Equals(""))
                locator = CellLocatorTemplate;
            if (!locator.ToString().Contains("{0}") || !locator.ToString().Contains("{1}"))
                throw new Exception("Can't create cell with locator template " /*+ cell.Locator */+ // TODO Locator missong
                        ". Template for Cell should contains '{0}' - for column and '{1}' - for row indexes.");
            cell.WebAvatar.ByLocator = WebDriverByUtils.FillByTemplate(locator, RowIndex, ColumnIndex);
            //cell.Avatar.Context.Add(ContextType.Locator, table.Locator); // TODO table.Locator missing
            return cell;
        }

        public ICell UpdateData(string colName, string rowName)
        {
            if ((ColumnName == null || ColumnName.Equals("")) && !(colName == null || colName.Equals("")))
                ColumnName = colName;
            if ((RowName == null || RowName.Equals("")) && !(rowName == null || rowName.Equals("")))
                RowName = rowName;
            return this;
        }
    }
}
