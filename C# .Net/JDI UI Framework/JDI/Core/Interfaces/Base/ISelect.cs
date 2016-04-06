using Epam.JDI.Core.Interfaces.Common;

namespace Epam.JDI.Core.Interfaces.Base
{
    public interface ISelect: IClickable, IText
    {
        /**
     * Selects WebElement. Similar to click()
     */
       //TODO [JDIAction]
        void Select();

        /**
         * return - Checks is WebElement selected
         */
        //TODO [JDIAction]
        bool Selected { get; }
    }
}
