using Epam.JDI.Core.Interfaces.Base;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces
{
    public interface ICell : ISelect
    {
        int ColumnNum { get; set; }

        int RowNum { get; set; }
    }
}