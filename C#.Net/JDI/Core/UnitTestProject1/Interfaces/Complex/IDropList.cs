using System;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Common;

namespace JDI_Core.Interfaces.Complex
{
    public interface IDropList : IDropList<IConvertible> { }
    public interface IDropList<in TEnum> : IMultiSelector<TEnum>, IText
        where TEnum : IConvertible
    {
    }
}
