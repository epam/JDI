using System;
using JDI_Core.Interfaces.Base;

namespace JDI_Core.Interfaces.Complex
{
    public interface ICheckList : ICheckList<IConvertible> { }
    public interface ICheckList<in TEnum> : IMultiSelector<TEnum>
        where TEnum : IConvertible
    {
    }
}
