using System;
using Epam.JDI.Core;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Web.Selenium.Base;
using Epam.JDI.Web.Selenium.Elements.Base;

namespace Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces
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