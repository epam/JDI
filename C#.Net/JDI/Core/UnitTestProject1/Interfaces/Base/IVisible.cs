namespace JDI_Core.Interfaces.Base
{
    public interface IVisible
    {
        /**
    *  Check is WebElement visible
    */
        //TODO:[JDIAction]
        bool Displayed { get; }

        /**
         * Check is WebElement hidden
         */
        //TODO:[JDIAction]
        bool Hidden { get; }

        /**
         * Waits while WebElement becomes visible
         */
        //TODO:[JDIAction]
        void WaitDisplayed();

        /**
         * Waits while WebElement becomes invisible
         */
        //TODO:[JDIAction]
        void WaitVanished();
    }
}
