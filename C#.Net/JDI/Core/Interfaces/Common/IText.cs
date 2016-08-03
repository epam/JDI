﻿using Epam.JDI.Core.Interfaces.Base;

namespace Epam.JDI.Core.Interfaces.Common
{
    public interface IText : IHasValue, IElement
    {
     /**
     * return: Get WebElement’s text
     */
        //TODO[JDIAction]
        string GetText { get; }

        /**
         * param: text Specify expected text
         * return: Wait while WebElement’s text contains expected text. Returns WebElement’s text
         */
        //TODO[JDIAction]
        string WaitText(string text);

        /**
         * @param regEx Specify expected regular expression Text
         * @return Wait while WebElement’s text matches regEx. Returns WebElement’s text
         */
        //TODO[JDIAction]
        string WaitMatchText(string regEx);
    }
}
