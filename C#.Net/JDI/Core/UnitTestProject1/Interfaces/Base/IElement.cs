namespace JDI_Core.Interfaces.Base
{
    public interface IElement : IBaseElement, IVisible
    {
        /**
    * Get element attribute
    *
    * param 'name' - Specify name for attribute
    * return - Returns chosen attribute
    */
        //TODO:[JDIAction]
        string GetAttribute(string name);

        /**
         * param 'name' - Specify attribute name
         * param 'value' - Specify attribute value
         * Waits while attribute gets expected value. Return false if this not happens
         */
        //TODO:[JDIAction]
        void WaitAttribute(string name, string value);

        /**
         * param 'attributeName' - Specify attribute name
         * param 'value'     -    Specify attribute value
         *                      Sets attribute value for WebElement
         */
        //TODO:[JDIAction]
        void SetAttribute(string attributeName, string value);
    }
}
