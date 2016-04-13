using System;


namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IRadioButtons : IRadioButtons<IConvertible> { }
    public interface IRadioButtons<in TEnum> : ISelector<TEnum>
        where TEnum : IConvertible
    {
    }
}
