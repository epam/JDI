using JDI_Web.Selenium.Elements.APIInteract;
using OpenQA.Selenium;

namespace JDI_Web.Selenium.Base
{
    public class GetElementType
    {
        private readonly By _locator;
        private GetElementModule _avatar;

        public GetElementType(By locator = null)
        {
            _locator = locator;
        }
        public GetElementType(By locator, WebBaseElement element)
        {
            _locator = locator;
            _avatar = element.WebAvatar;
        }
        public T Get<T>(T element, GetElementModule avatar) where T : WebBaseElement
        {
            try
            {
                return _locator == null
                    ? null
                    : (T) element.SetAvatar(avatar, _locator);
            }
            catch { return null; }
        }
    }
}
