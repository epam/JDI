using System;
using JDI_Core.Interfaces.Common;

namespace JDI_Core.Interfaces.Complex
{
    public interface IComboBox : ISelector<IConvertible> { }
    public interface IComboBox<in TEnum> : IDropDown<TEnum>, ITextField
        where TEnum : IConvertible
    {
    }
}
