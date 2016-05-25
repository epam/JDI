using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Commons;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;
using static Epam.JDI.Core.ExceptionUtils;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Rows : TableLine
    {
        public Rows()
        {
            HasHeader = false;
            ElementIndex = ElementIndexType.Nums;
            HeadersLocator = By.XPath(".//tr/td[1]");
            DefaultTemplate = By.XPath(".//tr[{0}]/td");
        }
        
        protected override Func<TableLine, IList<IWebElement>> GetHeadersAction => 
            row => Table.WebElement.FindElements(HeadersLocator);

        protected override IList<IWebElement> GetFirstLine()
        {
            return Table.Columns.GetLineAction(1);
        }

        public override Dictionary<string, Dictionary<string, ICell>> Get()
        {
            return Headers.ToDictionary(key => key, GetRow);
        }

        ///

        public IList<string> GetRowValue(string rowName)
        {
            return ActionWithException(() => Table.Rows.GetLineAction(rowName).Select(el => el.Text).ToList(),
                ex => $"Can't Get Row '{rowName}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetRowAsText(string rowName)
        {
            return GetRow(rowName).ToDictionary(pair => pair.Key, pair => pair.Value.Text);
        }

        public Dictionary<string, ICell> CellsToRow(ICollection<ICell> cells)
        {
            return cells.ToDictionary(
                    cell => Headers[cell.ColumnNum - 1],
                    cell => cell);
        }

        public Dictionary<string, ICell> GetRow(int rowNum)
        {
            if (Count < 0 || Table.Rows.Count < rowNum || rowNum <= 0)
                throw Exception($"Can't Get Row '{rowNum}'. [num] > ColumnsCount({Count}).");
            return ActionWithException(() =>
            {
                var colsCount = Table.Columns.Count;
                var webRow = Timer.GetResultByCondition(() => GetLineAction(rowNum), els => els.Count == colsCount);
                var result = new Dictionary<string, ICell>();
                for (var i = 0; i < colsCount; i++)
                    result.Add(Table.Columns.Headers[i], Table.Cell(webRow[i], new Column(i + 1), new Row(rowNum)));
                return result;
            }, ex => $"Can't Get Row '{rowNum}'. Reason: {ex}");
        }

        public IList<string> GetRowValue(int rowNum)
        {
            if (Count < 0 || Count < rowNum || rowNum <= 0)
                throw Exception($"Can't Get Row '{rowNum}'. [num] > ColumnsCount({Count}).");
            return ActionWithException(() => Table.Rows.GetLineAction(rowNum).Select(el => el.Text).ToList(),
                ex => $"Can't Get Row '{rowNum}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetRowAsText(int rowNum)
        {
            return GetRow(rowNum).ToDictionary(pair => pair.Key, pair => pair.Value.Text);
        }

        public Dictionary<string, ICell> GetRow(string rowName)
        {
            return ActionWithException(() =>
            {
                var colsCount = Table.Columns.Count;
                var webRowLine = Timer.GetResultByCondition(() => GetLineAction(rowName), els => els.Count == colsCount);
                var headers = Table.Columns.Headers;
                var webRow = SkipFirstColumn() ? webRowLine.ListCopy(1) : webRowLine;
                var result = new Dictionary<string, ICell>();
                for (var i = 0; i < colsCount; i++)
                    result.Add(Table.Columns.Headers[i], Table.Cell(webRow[i], new Column(name: headers[i]), new Row(name: rowName)));
                return result;
            }, ex => $"Can't Get Row '{rowName}'. Reason: {ex}");
        }

        public bool SkipFirstColumn()
        {
            return HasHeader && LineTemplate == null;
        }
    }
}