using System;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IMenu : IMenu<IConvertible> { }
    public interface IMenu<in TEnum> : ISelector<TEnum>
        where TEnum : IConvertible
    {
    }
}
