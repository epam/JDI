namespace JDI_Core.Interfaces.Base
{
    public interface ISetValue : IHasValue
    {
        /**
         * return - Get value of WebElement
         */
        new string Value { get; set; }
    }
}
