using System;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Common;

namespace JDI_Core.Interfaces.Complex
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
