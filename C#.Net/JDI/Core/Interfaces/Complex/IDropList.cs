using System;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IDropList : IDropList<IConvertible> { }
    public interface IDropList<in TEnum> : IMultiSelector<TEnum>, IText
        where TEnum : IConvertible
    {
    }
}
