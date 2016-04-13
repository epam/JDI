using System;
using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Base;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface ITextList : IBaseElement, IHasValue, IVisible
    {
        /**
         * @return Returns strings count
         */
        //TODO[JDIAction]
        int Count();

        /**
         * @return Wait while TextList’s text contains expected text. Returns WebElement’s text
         */
        //TODO[JDIAction]
        IList<string> WaitText(string str);

        /**
         * @return Return list of strings of TextList
         */
        //TODO[JDIAction]
        IList<string> Texts { get; }

        string this[int index] { get; set; }
    }
}
