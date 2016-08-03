﻿using Epam.JDI.Core.Interfaces.Base;

namespace Epam.JDI.Core.Interfaces.Common
{
    public interface ILink : IClickable, IText
    {
        /**
         * @return Get link destination
         */
        //TODO[JDIAction]
        string GetReference();

        /**
         * @param text Specify expected text
         * @return Wait while link destination contains expected text. Returns link destination
         */
        //TODO[JDIAction]
        string WaitReferenceContains(string text);

        /**
         * @param regEx Specify expected regular expression Text
         * @return Wait while link destination contains expected text. Returns link destination
         */
        //TODO[JDIAction]
        string WaitMatchReference(string regEx);

        /**
         * @return Get links tooltip
         */
        //TODO[JDIAction]
        string GetTooltip();
    }
}
