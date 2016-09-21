using System.Collections.Generic;
using System.Linq;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using OpenQA.Selenium;
using JDI_Commons;
using static Epam.JDI.Core.Settings.JDISettings;
using static Epam.JDI.Core.ExceptionUtils;
using static JDI_Web.Utils.WebExtensions;

namespace JDI_Web.Selenium.Elements.Complex.Table
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

        protected override IList<IWebElement> GetFirstLine => Table.Columns.GetLineAction(1);

        public override Dictionary<string, Dictionary<string, ICell>> Get()
        {
            return Headers.ToDictionary(key => key, GetRow);
        }

        ///

        public IList<string> GetRowValue(string rowName)
        {
            return ActionWithException(() => GetLineAction(rowName).Select(el => el.Text).ToList(),
                ex => $"Can't Get Row '{rowName}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetRowAsText(string rowName)
        {
            return GetRow(rowName).ToDictionary(pair => pair.Key, pair => pair.Value.GetText);
        }

        public Dictionary<string, ICell> CellsToRow(ICollection<ICell> cells)
        {
            return cells.ToDictionary(
                    cell => Headers[cell.ColumnNum - 1],
                    cell => cell);
        }

        public Dictionary<string, ICell> GetRow(int rowNum)
        {
            if (Count < 0 || Count < rowNum || rowNum <= 0)
                throw Exception($"Can't Get Row '{rowNum}'. [num] > ColumnsCount({Count}).");
            return ActionWithException(() =>
            {
                var colsCount = Table.Columns.Count;
                var webRow = Timer.GetResultByCondition(() => GetLineAction(rowNum), els => els.Count >= colsCount);
                if (webRow == null)
                    throw Exception($"Table has only {GetLineAction(rowNum).Count} columns " +
                                    $"but expected at least {colsCount}");
                var result = new Dictionary<string, ICell>();
                if (webRow.Count == colsCount)
                {
                    AddCols(result, Table.Columns.Headers, webRow, rowNum);
                    return result;
                }
                AddCols(result, Table.Columns.AllHeaders, webRow, rowNum);
                var simplifiedHeaders = Table.Columns.Headers.Select(Simplify);
                return result.Where(el => simplifiedHeaders.Contains(Simplify(el.Key))).ToDictionary();
            }, ex => $"Can't Get Row '{rowNum}'. Reason: {ex}");
        }
        private void AddCols(Dictionary<string, ICell> result, IList<string> headers, IList<IWebElement> webRow, int rowNum)
        {
            for (var i = 0; i < headers.Count; i++)
            {
                var label = headers[i];
                if (!result.ContainsKey(label))
                    result.Add(label, Table.Cell(webRow[i], new Column(i + 1), new Row(rowNum)));
            }
        }

        public IList<string> GetRowValue(int rowNum)
        {
            if (Count < 0 || Count < rowNum || rowNum <= 0)
                throw Exception($"Can't Get Row '{rowNum}'. [num] > ColumnsCount({Count}).");
            return ActionWithException(() => GetLineAction(rowNum).Select(el => el.Text).ToList(),
                ex => $"Can't Get Row '{rowNum}'. Reason: {ex}");
        }

        public Dictionary<string, string> GetRowAsText(int rowNum)
        {
            return GetRow(rowNum).ToDictionary(pair => pair.Key, pair => pair.Value.GetText);
        }

        public Dictionary<string, ICell> GetRow(string rowName)
        {
            return ActionWithException(() =>
            {
                var colsCount = Table.Columns.Count;
                var webRow = Timer.GetResultByCondition(() => GetLineAction(rowName), els => els.Count >= colsCount);
                if (webRow == null)
                    throw Exception($"Table has only {GetLineAction(rowName).Count} columns " +
                                    $"but expected at least {colsCount}");
                var result = new Dictionary<string, ICell>();
                if (webRow.Count == colsCount)
                {
                    AddCols(result, Table.Columns.Headers, webRow, rowName);
                    return result;
                }
                AddCols(result, Table.Columns.AllHeaders, webRow, rowName);
                return result.Where(el => Table.Columns.Headers.Contains(el.Key)).ToDictionary();
            }, ex => $"Can't Get Row '{rowName}'. Reason: {ex}");
        }
        private void AddCols(Dictionary<string, ICell> result, IList<string> headers, IList<IWebElement> webRow, string rowName)
        {
            for (var i = 0; i < headers.Count; i++)
                result.Add(headers[i], Table.Cell(webRow[i], new Column(name: headers[i]), new Row(name: rowName)));
        }
    }
}