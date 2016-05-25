using JDI_Web.Selenium.Elements.APIInteract;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Base
{
    public class GetElementType
    {
        private readonly By _locator;

        public GetElementType(By locator = null)
        {
            _locator = locator;
        }
        public T Get<T>(T element, GetElementModule avatar) where T : WebBaseElement
        {
            return _locator == null
                    ? null
                    : (T)element.SetAvatar(avatar, _locator);
        }
    }
}
