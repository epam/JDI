﻿using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using JDI_Commons;
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

        protected override Func<TableLine, IList<IWebElement>> GetHeadersAction =>
            row =>
            {
                var headers = Table.WebElement.FindElements(HeadersLocator);
                return Table.Rows.SkipFirstColumn() ? headers.ListCopy(1) : headers;
            };

        protected override IList<IWebElement> GetFirstLine()
        {
            var line = Table.Rows.GetLineAction(1);
            return Table.Rows.SkipFirstColumn() ? line.ListCopy(1) : line;
        }
        
        public Dictionary<string, ICell> GetColumn(string colName)
        {
            return ActionWithException(() =>
            {
                var rowsCount = Table.Rows.Count;
                var headers = Table.Rows.Headers;
                var webColumn = Timer.GetResultByCondition(() => GetLineAction(colName), els => els.Count == rowsCount);
                var result = new Dictionary<string, ICell>();
                for (var i = 0; i < rowsCount; i++)
                    result.Add(Table.Rows.Headers[i], Table.Cell(webColumn[i], new Column(name: colName), new Row(name: headers[i])));
                return result;
            }, ex => $"Can't Get Column '{colName}'. Reason: {ex}");
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

        public Dictionary<string, ICell> CellsToColumn(Collection<ICell> cells)
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
                    els => els.Count == rowsCount);
                var result = new Dictionary<string, ICell>();
                for (var i = 0; i < rowsCount; i++)
                    result.Add(Table.Rows.Headers[i],
                        Table.Cell(webColumn[i], new Column(colNum), new Row(i + 1)));
                return result;
            }, ex => $"Can't Get Column '{colNum}'. Reason: {ex}");
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