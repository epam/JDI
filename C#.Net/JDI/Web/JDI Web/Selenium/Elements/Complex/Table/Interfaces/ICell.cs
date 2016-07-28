using System;
using Epam.JDI.Core.Interfaces.Base;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;

namespace JDI_Web.Selenium.Elements.Complex.Table.Interfaces
{
    public interface ICell : ISelect
    {
        SelectableElement Get();
        T Get<T>(T element) where T : WebBaseElement;
        T Get<T>(Type clazz) where T : WebBaseElement;
        int ColumnNum { get; }
        int RowNum { get; }
        string ColumnName { get; }
        string RowName { get; }
    }
}