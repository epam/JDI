namespace JDI_Web.Utils
{
    public static class WebExtensions
    {
        // TODO Old code
        /*
        public static T GetVIElement<T>(this T element) where T: IVIElement
        {
            element.GetWebElement();
            return element;
        }
        
        public static string Print(this IEnumerable<IHaveValue> list, string separator = ", ", string format = "{0}")
        {
            return (list != null) ? string.Join(separator, list.Select(el => string.Format(format, el.Value))) : "";
        }

        public static T UseAsTemplate<T>(this T element, string id) where T : VIElement
        {
            element.TemplateId = id;
            return element;
        }

        public static T WaitTimeout<T>(this T viElement, int timeoutInSec) where T : IVIElement
        {
            viElement.SetWaitTimeout(timeoutInSec);
            return viElement;
        }
        */
    }
}
