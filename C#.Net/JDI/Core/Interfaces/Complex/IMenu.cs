using System;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IMenu : IMenu<IConvertible> { }
    public interface IMenu<in TEnum> : ISelector<TEnum>
        where TEnum : IConvertible
    {
        void Hover(params string[] names);

        void Hover(TEnum name);
        void Select(params string[] names);
        void HoverAndClick(params string[] names);
        void HoverAndClick(TEnum name);
        void HoverAndSelect(params string[] name);

        void HoverAndSelect(TEnum name);
    }
}
