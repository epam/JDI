using JDI_Core.Interfaces.Base;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;

namespace JDI_Web.Selenium.Elements.Complex.table.interfaces
{
    public interface ICell : ISelect
    {
        SelectableElement Get();
        T Get<T>(T element) where T : WebBaseElement;
        int ColumnNum { get; }
        int RowNum { get; }
        string ColumnName { get; }
        string RowName { get; }
    }
}