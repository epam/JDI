using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using Epam.JDI.Commons;
using Epam.JDI.Web.Selenium.Elements.APIInteract;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;
using RestSharp.Extensions;
using static System.TimeSpan;
using static Epam.JDI.Core.Settings.JDISettings;


namespace Epam.JDI.Web.Selenium.Elements.Complex.table
{
    public class Table : Textbox, ITable
    {
        private IList<ICell> _allCells = new List<ICell>();
        private readonly Columns _columns = new Columns();
        private readonly Rows _rows = new Rows();
        private IList<string> _footer;

        private IList<ICell> AllCells
        {
            get
            {
                var result = new List<ICell>();
                var rows = Rows.Get();
                Columns.Headers.ForEach(columnName => Rows.Headers
                    .ForEach(rowName => result.Add(rows[rowName][columnName])));

                if (Cache)
                    AllCells = result;
                return result;
            }
            set { _allCells = value; }
        }

        public Columns Columns
        {
            get { return _columns; }
            set { _columns.Update(value); }
        }

        public Rows Rows
        {
            get { return _rows; }
            set { _rows.Update(value); }
        }

        public bool Cache { set; get; }
        protected By CellLocatorTemplate { set; get; }
        public By FooterLocator { get; set; } = By.XPath(".//tfoot/tr/th");

        public Table()
        {
            Columns.Table = this;
            Rows.Table = this;
        }

        public Table(By locator) : base(locator)
        {
            Columns.Table = this;
            Rows.Table = this;
        }

        public Table(By columnHeader, By row, By column) : this()
        {
            if (column != null)
                Columns.LineTemplate = column;
            if (columnHeader != null)
                Columns.HeadersLocator = columnHeader;
            if (row != null)
                Rows.LineTemplate = row;
        }

        public Table(By rowHeader, By columnHeader, By row, By column, int rowStartIndex, int columnStartIndex) : this()
        {
            if (column != null)
                Columns.LineTemplate = column;
            if (columnHeader != null)
                Columns.HeadersLocator = columnHeader;
            if (row != null)
                Rows.LineTemplate = row;
            if (rowHeader != null)
                Rows.HeadersLocator = rowHeader;

            if (columnStartIndex > -1)
                Columns.StartIndex = columnStartIndex;
            if (rowStartIndex > -1)
                Rows.StartIndex = rowStartIndex;
        }

        public Table(By tableLocator, By cellLocatorTemplate) : this(tableLocator)
        {
            CellLocatorTemplate = cellLocatorTemplate;
        }

        public Table(By columnHeader, By rowHeader, By row, By column, By footer, TableSettings settings,
            int columnStartIndex, int rowStartIndex) : this()
        {
            Columns.LineTemplate = column;
            if (columnHeader != null)
                Columns.HeadersLocator = columnHeader;
            Rows.LineTemplate = row;
            if (rowHeader != null)
                Rows.HeadersLocator = rowHeader;
            FooterLocator = footer;

            Columns.StartIndex = columnStartIndex;
            Rows.StartIndex = rowStartIndex;

            SetTableSettings(settings);
        }

        public Table(TableSettings settings) : this()
        {
            SetTableSettings(settings);
        }


        public Table Copy()
        {
            return (Table) Clone();
        }

        public Table Clone()
        {
            var newTable = new Table();
            newTable.Rows = Rows.Clone(new Rows(), newTable);
            newTable.Columns = Columns.Clone(new Columns(), newTable);
            newTable.Avatar = new GetElementModule {ByLocator = Locator, Element = newTable};
            newTable.Parent = Parent;
            return newTable;
        }

        public void SetTableSettings(TableSettings settings)
        {
            Rows.HasHeader = settings.RowHasHeaders;
            Rows.Headers = settings.RowHeaders;
            Rows.Count = settings.RowsCount;
            Columns.HasHeader = settings.ColumnHasHeaders;
            Columns.Headers = settings.ColumnHeaders;
            Columns.Count = settings.ColumnsCount;
        }

        public ITable UseCache()
        {
            Cache = true;
            return this;
        }

        public void Clean()
        {
            AllCells = new List<ICell>();
            Columns.Clean();
            Rows.Clean();
        }

        public void Clear()
        {
            Clean();
        }

        Columns ITable.Columns()
        {
            throw new NotImplementedException();
        }

        public Dictionary<string, ICell> Column(int colNum)
        {
            return Columns.GetColumn(colNum);
        }

        public Dictionary<string, ICell> Column(string colName)
        {
            return Columns.GetColumn(colName);
        }

        public Dictionary<string, ICell> Column(string value, Row row)
        {
            var columnCell = Cell(value, row);
            return columnCell != null ? Columns.GetColumn(columnCell.ColumnNum) : null;
        }

        Rows ITable.Rows()
        {
            throw new NotImplementedException();
        }

        public Dictionary<string, ICell> Row(string value, Column column)
        {
            var rowCell = Cell(value, column);
            return rowCell != null ? Rows.GetRow(rowCell.RowNum) : null;
        }

        public IList<string> ColumnValue(int colNum)
        {
            return Columns.GetColumnValue(colNum);
        }

        public IList<string> ColumnValue(string colName)
        {
            return Columns.GetColumnValue(colName);
        }

        private Dictionary<string, ICell> Column(Column column)
        {

            return column.Get(Column, Column);
        }

        public Dictionary<string, ICell> Row(int rowNum)
        {
            return Rows.GetRow(rowNum);
        }

        public Dictionary<string, ICell> Row(string rowName)
        {
            return Rows.GetRow(rowName);
        }

        public IList<string> RowValue(int rowNum)
        {
            return Rows.GetRowValue(rowNum);
        }

        public IList<string> RowValue(string rowName)
        {
            return Rows.GetRowValue(rowName);
        }

        private Dictionary<string, ICell> Row(Row row)
        {
            return row.Get(Row, Row);
        }

        public ITable HasAllHeaders()
        {
            Columns.HasHeader = true;
            Rows.HasHeader = true;
            return this;
        }

        public ITable HasNoHeaders()
        {
            Columns.HasHeader = false;
            Rows.HasHeader = false;
            return this;
        }

        public ITable HasOnlyColumnHeaders()
        {
            Columns.HasHeader = true;
            Rows.HasHeader = false;
            return this;
        }

        public ITable HasOnlyRowHeaders()
        {
            Rows.HasHeader = true;
            return this;
        }


        public ITable HasColumnHeaders(IList<string> value)
        {
            Columns.HasHeader = true;
            Columns.AddHeaders(value);
            return this;
        }

        public ITable HasColumnHeaders(Type headers)
        {
            return HasColumnHeaders(GetAllEnumNames(headers));
        }

        public ITable HasRowHeaders(IList<string> value)
        {
            Rows.HasHeader = true;
            Rows.AddHeaders(value);
            return this;
        }

        public ITable HasRowHeaders(Type headers)
        {
            return HasRowHeaders(GetAllEnumNames(headers));
        }

        private IList<string> GetAllEnumNames(Type headers)
        {
            return typeof (Enum).IsAssignableFrom(headers)
                ? Enum.GetNames(headers).ToList()
                : headers.GetFields().ToList().Select(el => el.GetValue(null).ToString()).ToList();
        }

        public ITable SetColumnsCount(int value)
        {
            Columns.Count = value;
            return this;
        }

        public ITable SetRowsCount(int value)
        {
            Rows.Count = value;
            return this;
        }

        protected IList<string> GetFooterAction()
        {
            return WebElement.FindElements(FooterLocator).Select(e => e.Text).ToList();
        }


        public IList<string> Footer
        {
            get { return new List<string>(_footer); }
            set { _footer = new List<string>(value); }
        }

        public Dictionary<string, SelectableElement> Header()
        {
            return Columns.Header();
        }

        public SelectableElement Header(string name)
        {
            return Columns.Header(name);
        }

        public IList<string> Headers()
        {
            return Columns.Headers;
        }

        IList<string> ITable.Footer()
        {
            throw new NotImplementedException();
        }

        public IList<string> FooterInstance() // TODO method name conflict
        {
            if (Footer != null)
                return Footer;
            //_footer = invoker.doJActionResult("Get Footer", this::getFooterAction); TODO
            if (Footer == null || Footer.Count == 0)
                return new List<string>();
            Columns.Count = Footer.Count;
            return Footer;
        }

        public ICell Cell(Column column, Row row)
        {
            int colIndex = column.Get(GetColumnIndex, num => num + Columns.StartIndex - 1);
            int rowIndex = (int) row.Get(GetRowIndex, num => num + Rows.StartIndex - 1);
            return AddCell(colIndex, rowIndex,
                column.Get(name => Columns.Headers.IndexOf(name) + 1, num => num),
                row.Get(name => Rows.Headers.IndexOf(name) + 1, num => num),
                column.Get(name => name, num => ""),
                row.Get(name => name, num => ""));
        }

        public ICell Cell(string columnName, string rowName)
        {
            return Cell(table.Column.column(columnName), table.Row.row(rowName));
        }

        public ICell Cell(int columnIndex, int rowIndex)
        {
            return Cell(table.Column.column(columnIndex), table.Row.row(rowIndex));
        }

        public ICell Cell(IWebElement webElement, Column column, Row row)
        {
            return AddCell(webElement,
                column.Get(name => Columns.Headers.IndexOf(name) + 1, num => num),
                row.Get(name => Rows.Headers.IndexOf(name) + 1, num => num),
                column.Get(name => name, num => ""),
                row.Get(name => name, num => ""));
        }

        private IList<ICell> Matches(Collection<ICell> list, string regex)
        {
            return new List<ICell>(list.Where(cell => cell.Value.Matches(regex)));
        }

        public IList<ICell> Cells(string value)
        {
            return new List<ICell>(GetCells().Where(cell => cell.Value.Equals(value)));
        }


        public ICell Cell(string value)
        {
            return
                Rows.Get()
                    .Select(row => row.Value.FirstOrDefault(pair => pair.Value.Text.Equals(value)).Value)
                    .FirstOrDefault(result => result != null);
        }

        public IList<ICell> GetCells()
        {
            var rows = Rows.Get();
            var result =
                (from columnName in Columns.Headers from rowName in Rows.Headers select rows[rowName][columnName])
                    .ToList();
            if (Cache)
                AllCells = result;
            return result;
        }

        public Dictionary<string, Dictionary<string, ICell>> GetRows(params string[] colNameValues)
            // TODO method name conflict
        {
            var result = new Dictionary<string, Dictionary<string, ICell>>();
            foreach (var row in Rows.Get())
            {
                var matches = true;
                foreach (var colNameValue in colNameValues)
                {
                    if (!colNameValue.Matches("[^=]+=[^=]*"))
                        throw Exception($"Wrong searchCriteria for Cells: {colNameValue}");
                    var splitted = colNameValue.Split(Convert.ToChar("="));
                    var colName = splitted[0];
                    var colValue = splitted[1];
                    var cell = row.Value[colName];
                    if (cell == null || !cell.Value.Equals(colValue))
                    {
                        matches = false;
                        break;
                    }
                }
                if (matches) result.Add(row.Key, row.Value);
            }
            return result;
        }

        public Dictionary<string, Dictionary<string, ICell>> GetColumns(params string[] rowNameValues)
            // TODO method name conflict
        {
            var result = new Dictionary<string, Dictionary<string, ICell>>();
            foreach (var column in Columns.Get())
            {
                var matches = true;
                foreach (var rowNameValue in rowNameValues)
                {
                    if (!rowNameValue.Matches("[^=]+=[^=]*"))
                        throw Exception($"Wrong searchCritaria for Cells: {rowNameValue}");
                    var splitted = rowNameValue.Split(Convert.ToChar("="));
                    var rowName = splitted[0];
                    var rowValue = splitted[1];
                    var cell = column.Value[rowName];
                    if (cell == null || !cell.Value.Equals(rowValue))
                    {
                        matches = false;
                        break;
                    }
                }
                if (matches) result.Add(column.Key, column.Value);
            }
            return result;
        }


        public bool WaitValue(string value, Row row)
        {
            return Timer.Wait(() => Column(value, row) != null);
        }

        public bool WaitValue(string value, Column column)
        {
            return Timer.Wait(() => Row(value, column) != null);
        }

        public bool Empty
        {
            get {
                WebDriver.Manage().Timeouts().ImplicitlyWait(Zero);
                var rowsCount = Rows.GetCount(true);
                WebDriver.Manage().Timeouts().ImplicitlyWait(FromSeconds(Timeouts.CurrentTimeoutSec));
                return rowsCount == 0;
            }
        }

    public bool WaitHaveRows()
        {
            return WaitRows(1);
        }

        public bool WaitRows(int count)
        {
            return Timer.Wait(() => Rows.Count >= count);
        }

        public ICell Cell(string value, Column column)
        {
            var colIndex = column.Get(name => Columns.Headers.IndexOf(name) + 1, num => num);
            return Columns.GetColumn(colIndex).FirstOrDefault(pair => pair.Value.Value.Equals(value)).Value;
        }

        public ICell Cell(string value, Row row)
        {
            var rowNum = row.HasName ? Rows.Headers.IndexOf(row.Name) + 1 : row.Num;
            return Rows.GetRow(rowNum).FirstOrDefault(pair => pair.Value.Value.Equals(value)).Value;
        }

        public IList<ICell> CellsMatch(string regex)
        {
            return Matches(new Collection<ICell>(GetCells()), regex);
        }
        public ICell CellMatch(string regex)
        {
            return Rows.Get().Select(row => row.Value.FirstOrDefault(pair => pair.Value.Text.Matches(regex)).Value).FirstOrDefault(result => result != null);
        }
        
        public IList<ICell> CellsMatch(string regex, Column column)
        {
            var columnLine = Column(column);
            return new List<ICell>(((Dictionary<string, ICell>)columnLine.Where(v => v.Key.Matches(regex))).Values);
        }

        public IList<ICell> CellsMatch(string regex, Row row)
        {
            var columnLine = Row(row);
            return new List<ICell>(((Dictionary<string, ICell>)columnLine.Where(v => v.Key.Matches(regex))).Values);
        }

        private int GetColumnIndex(string name)
        {
            int nameIndex;
            var headers = Columns.Headers;
            if (headers != null && headers.Contains(name))
                nameIndex = headers.IndexOf(name);
            else
            throw Exception("Can't Get Column: '" + name + "'. " + ((headers == null)
                    ? "ColumnHeaders is Null"
                    : "Available ColumnHeaders: " + headers.Print(", ", "'{0}'") + ")"));
            return nameIndex + Columns.StartIndex;
        }

        private object GetRowIndex(string name)
        {
            int nameIndex;
            var headers = Rows.Headers;
            if (headers != null && headers.Contains(name))
                nameIndex = headers.IndexOf(name);
            else
                throw Exception($"Can't Get Row: {name}. Available RowHeaders: {Headers().Print(", ", "'{0}'")}"); 
            return nameIndex + Rows.StartIndex;
        }

        protected new string GetTextAction()
        {
            return "||X||" + Columns.Headers.Print("|") + "||\n" +
                    Rows.Headers.Select(rowName => 
                        "||" + rowName + "||" + GetCells().Where(cell => 
                            cell.RowName.Equals(rowName)).Select(cell => cell.Value)
                            .Print("|") + "||")
                                .Print("\n");
        }

        private ICell AddCell(int colIndex, int rowIndex, int colNum, int rowNum, string colName, string rowName)
        {
            var cell = (Cell) AllCells.FirstOrDefault(c => c.ColumnNum == colNum && c.RowNum == rowNum);
            if (cell != null)
                return cell.UpdateData(colName, rowName);
            cell = new Cell(colNum, rowNum, colName, rowName, CellLocatorTemplate, this, colIndex, rowIndex);
            cell.SetAvatar((GetElementModule) cell.Get().Avatar);

            if (Cache)
                AllCells.Add(cell);
            return cell;
        }

        private ICell AddCell(IWebElement webElement, int colNum, int rowNum, string colName, string rowName)
        {
            var cell = (Cell) _allCells.FirstOrDefault(c => c.ColumnNum == colNum && c.RowNum == rowNum);
            if (cell != null)
            {
                cell.WebElement = webElement;
                return cell.UpdateData(colName, rowName);
            }
            cell = new Cell(colNum, rowNum, colName, rowName, CellLocatorTemplate, this, -1, webElement: webElement);

            if (Cache)
                AllCells.Add(cell);
            return cell;
        }
    }
}