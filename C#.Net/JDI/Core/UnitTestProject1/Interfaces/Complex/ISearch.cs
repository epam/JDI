using System.Collections.Generic;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Common;

namespace JDI_Core.Interfaces.Complex
{
    public interface ISearch : ITextField, IComposite
    {

        /**
         * @param text        Specify Text to search
         * @param selectValue Specify value to choose from suggested search result
         *                    Input text in search and then select value from suggestions
         */
        //TODO[JDIAction]
    void ChooseSuggestion(string text, string selectValue);

        /**
         * @param text        Specify Text to search
         * @param selectIndex Specify index to choose from suggested search result
         *                    Input text in search and then select suggestions by index
         */
        //TODO[JDIAction]
        void ChooseSuggestion(string text, int selectIndex);

        /**
         * @param text Specify Text to search
         *             Input text in search field and press search button
         */
        //TODO[JDIAction]
        void Find(string text);

        /**
         * @param text Specify Text to search
         * @return Select all suggestions for text
         */
        //TODO[JDIAction]
        IList<string> GetSuggesions(string text);
    }
}
