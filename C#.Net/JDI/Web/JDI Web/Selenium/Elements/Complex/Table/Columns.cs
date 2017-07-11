using System.Collections.Generic;
using System.Linq;
using JDI_Commons;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using OpenQA.Selenium;
using static Epam.JDI.Core.Settings.JDISettings;
using static Epam.JDI.Core.ExceptionUtils;

namespace JDI_Web.Selenium.Elements.Complex.Table
{
    public class Columns : TableLine
    {
        public Columns()
        {
            HasHeader = true;
            ElementIndex = ElementIndexType.Nums;
            HeadersLocator = By.XPath(".//th");
            DefaultTemplate = By.XPath(".//tr/td[{0}]");
        }

        protected List<IWebElement> GetHeadersAction()
        {
            return Table.WebElements.FindAll(el => el.Equals(HeadersLocator));
        }

        protected override IList<IWebElement> GetFirstLine => Table.Rows.GetLineAction(1);
        
        public Dictionary<string, ICell> GetColumn(string colName)
        {
            return ActionWithException(() =>
            {
                var rowsCount = Table.Rows.Count;
                var webColumn = Timer.GetResultByCondition(() => GetLineAction(colName), els => els.Count >= rowsCount);
                if (webColumn == null)
                    throw Exception($"Table has only {GetLineAction(colName).Count} columns " +
                                    $"but expected at least {rowsCount}");

                var result = new Dictionary<string, ICell>();
                if (webColumn.Count == rowsCount)
                {
                    AddRows(result, Table.Rows.Headers, webColumn, colName);
                    return result;
                }
                AddRows(result, Table.Rows.AllHeaders, webColumn, colName);
                return result.Where(el => Table.Rows.Headers.Contains(el.Key)).ToDictionary();
            }, ex => $"Can't Get Column '{colName}'. Reason: {ex}");
        }

        private void AddRows(Dictionary<string, ICell> result, IList<string> headers, IList<IWebElement> webColumn, string colName)
        {
            for (var i = 0; i < headers.Count; i++)
            {
                var label = headers[i];
                if (!result.ContainsKey(label))
                    result.Add(label, Table.Cell(webColumn[i], new Column(name: colName), new Row(name: label)));
            }
        }

        public IList<string> GetColumnValue(string colName)
        {
            return
                ActionWithException(
                    () => GetLineAction(colName).Select(el => el.Text).ToList(), 
                    ex => $"Can't Get Column '{colName}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetColumnAsText(string colName)
        {
            return GetColumn(colName).ToDictionary(pair => pair.Key, pair => pair.Value.GetText);
        }

        public Dictionary<string, ICell> CellsToColumn(IEnumerable<ICell> cells)
        {
            return cells.ToDictionary(cell => Headers[cell.RowNum - 1], cell => cell);
        }

        public Dictionary<string, ICell> GetColumn(int colNum)
        {
            if (Count < 0 || Count < colNum || colNum <= 0)
                throw Exception($"Can't Get Column '{colNum}'. [num] > RowsCount({Count}).");
            return ActionWithException(() =>
            {
                var rowsCount = Table.Rows.Count;
                var webColumn = Timer.GetResultByCondition(() => GetLineAction(colNum),
                    els => els.Count >= rowsCount);
                if (webColumn == null)
                    throw Exception($"Table has only {GetLineAction(colNum).Count} columns " +
                                    $"but expected at least {rowsCount}");
                var result = new Dictionary<string, ICell>();
                if (webColumn.Count == rowsCount)
                {
                    AddRows(result, Table.Rows.Headers, webColumn, colNum);
                    return result;
                }
                AddRows(result, Table.Rows.AllHeaders, webColumn, colNum);
                return result.Where(el => Table.Rows.Headers.Contains(el.Key)).ToDictionary();
            }, ex => $"Can't Get Column '{colNum}'. Reason: {ex}");
        }

        /*private Dictionary<string, Dictionary<string, ICell>> WithValueByRule(
            Row row, JFuncTTREx<string, string, bool> func)
        {
            List<string> rowNames = row.HasName
                ? Table.Rows.GetRowAsText(row.Name).where(func).keys()
                : Table.Rows.GetRowAsText(row.Name).where(func).keys();
            return new Dictionary<>(rowNames, key=>key, this::getColumn);
        }*/

        private void AddRows(Dictionary<string, ICell> result, IList<string> headers, IList<IWebElement> webColumn, int colNum)
        {
            for (var i = 0; i < headers.Count; i++)
                result.Add(headers[i], Table.Cell(webColumn[i], new Column(colNum), new Row(i + 1)));
        }

        public IList<string> GetColumnValue(int colNum)
        {
            if (Count < 0 || Count < colNum || colNum <= 0)
                throw Exception($"Can't Get Column '{colNum}'. [num] > RowsCount({Count}).");
            return ActionWithException(() => GetLineAction(colNum).Select(el => el.Text).ToList(),
                ex => $"Can't Get Column '{colNum}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetColumnAsText(int colNum)
        {
            return GetColumn(colNum).ToDictionary(pair => pair.Key, pair => pair.Value.GetText);
        }

        public override Dictionary<string, Dictionary<string, ICell>> Get()
        {
            return Headers.ToDictionary(key =>key, GetColumn);
        }
        
    }
}