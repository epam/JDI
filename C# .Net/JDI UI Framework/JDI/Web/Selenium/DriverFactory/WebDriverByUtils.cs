using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Commons;
using OpenQA.Selenium;
using static System.String;

namespace Epam.JDI.Web.Selenium.DriverFactory
{
    public static class WebDriverByUtils
    {
        public static Func<string, By> GetByFunc(this By by)
        {
            return ByTypes.FirstOrDefault(el => by.ToString().Contains(el.Key)).Value;
        }
        private static string GetBadLocatorMsg(this string byLocator, params object[] args)
        {
            return "Bad locator template '" + byLocator + "'. Args: " + args.Select(el => el.ToString()).Print(", ", "'%s'") + ".";
        }
        public static By FillByTemplate(this By by, params object[] args)
        {
            var byLocator = by.GetByLocator();
            if (!byLocator.Contains("%"))
                throw new Exception(GetBadLocatorMsg(byLocator, args));
            byLocator = ExceptionUtils.ActionWithException(
                () => Format(byLocator, args),
                ex => GetBadLocatorMsg(byLocator, args));
            return by.GetByFunc().Invoke(byLocator);
        }

        public static string GetByLocator(this By by)
        {
            var byAsString = by.ToString();
            var index = byAsString.IndexOf(": ") + 2;
            return byAsString.Substring(index);
        }

        private static readonly Dictionary<string, Func<string, By>> ByTypes = new Dictionary<string, Func<string, By>>
        {
            {"CssSelector", By.CssSelector},
            {"ClassName", By.ClassName},
            {"Id", By.Id},
            {"LinkText", By.LinkText},
            {"Name", By.Name},
            {"PartialLinkText", By.PartialLinkText},
            {"TagName", By.TagName},
            {"XPath", By.XPath},

        };
    }
}
