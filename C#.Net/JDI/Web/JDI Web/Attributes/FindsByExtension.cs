using System.Reflection;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace JDI_Web.Attributes
{
    public static class FindsByExtension
    {
        public static By GetFindsBy(this FieldInfo field)
        {
            var findBy = field.GetCustomAttribute<FindsByAttribute>(false);
            if (findBy == null)
                return null;
            var how = findBy.How;
            var @using = findBy.Using;
            switch (how)
            {
                case How.Id:
                    return By.Id(@using);
                case How.Name:
                    return By.Name(@using);
                case How.TagName:
                    return By.TagName(@using);
                case How.ClassName:
                    return By.ClassName(@using);
                case How.CssSelector:
                    return By.CssSelector(@using);
                case How.LinkText:
                    return By.LinkText(@using);
                case How.PartialLinkText:
                    return By.PartialLinkText(@using);
                case How.XPath:
                    return By.XPath(@using);
                default:
                    return null;
            }
        }
    }
}
