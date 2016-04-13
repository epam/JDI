using System;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IDropDown : IDropDown<IConvertible> { }
    public interface IDropDown<in TEnum> : ISelector<TEnum>, IText, IClickable
        where TEnum : IConvertible
    {
        /**
     * Expanding DropDown
     */
        //TODO[JDIAction]
        void Expand();

        /**
         * Closing DropDown
         */
        //TODO[JDIAction]
        void Close();
    }
}
