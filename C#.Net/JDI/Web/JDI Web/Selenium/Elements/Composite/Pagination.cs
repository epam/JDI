using System;
using System.Linq;
using JDI_Commons;
using JDI_Core.Attributes;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Complex;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;
using static JDI_Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Elements.Composite
{
    public class Pagination : WebBaseElement, IPagination
    {
        public By NextLocator;
        public By PreviousLocator;
        public By FirstLocator;
        public By LastLocator;

        public Pagination(By pageTemplateLocator = null, By nextLocator = null, By previousLocator = null,
                          By firstLocator = null, By lastLocator = null)
            : base(pageTemplateLocator)
        {
            NextLocator = nextLocator;
            PreviousLocator = previousLocator;
            FirstLocator = firstLocator;
            LastLocator = lastLocator;
        }

        public void Next()
        {
            Invoker.DoJAction("Choose Next page", p => ((Pagination)p).NextAction(this).Click());
        }

        public void Previous()
        {
            Invoker.DoJAction("Choose Previous page", p => ((Pagination)p).PreviousAction(this).Click());
        }

        public void First()
        {
            Invoker.DoJAction("Choose First page", p => ((Pagination)p).FirstAction(this).Click());
        }

        public void Last()
        {
            Invoker.DoJAction("Choose Last page", p => ((Pagination)p).LastAction(this).Click());
        }

        public void SelectPage(int index)
        {
            Invoker.DoJAction($"Choose '{index}' page", p => ((Pagination)p).PageAction(this, index).Click());
        }

        private Clickable GetClickable(string name)
        {
            var fields = this.GetFields(typeof(IClickable));
            var result = fields.FirstOrDefault(field => NameAttribute.GetElementName(field).ToLower().Contains(name.ToLower()));
            return (Clickable) result?.GetValue(this);
        }

        private string CantChooseElementMsg(string actionName, string shortName, string action)
        {
            return $@"Can't choose {actionName} page for Element '{ToString()}'. 
Please specify locator for this action using constructor or add Clickable Element on pageObject 
with name '{shortName}Link' or '{shortName}Button' or use locator template with parameter '{shortName}' 
or override {action}() in class";
        }

        public Func<Pagination, Clickable> NextAction = p =>
        {
            const string shortName = "next";
            if (p.NextLocator != null)
                return new Clickable(p.NextLocator);

            var nextLink = p.GetClickable(shortName);
            if (nextLink != null)
                return nextLink;

            if (p.Locator != null && p.Locator.ToString().Contains("{0}"))
                return new Clickable(p.Locator.FillByTemplate(shortName));
            throw Exception(p.CantChooseElementMsg("Next", shortName, "nextAction"));
        };

        public Func<Pagination, Clickable> PreviousAction = p =>
        {
            const string shortName = "prev";
            if (p.PreviousLocator != null)
                return new Clickable(p.PreviousLocator);

            var prevLink = p.GetClickable(shortName);
            if (prevLink != null)
                return prevLink;

            if (p.Locator != null && p.Locator.ToString().Contains("{0}"))
                return new Clickable(p.Locator.FillByTemplate(shortName));
            throw Exception(p.CantChooseElementMsg("Previous", shortName, "previousAction"));
        };

        public Func<Pagination, Clickable> FirstAction = p =>
        {
            const string shortName = "first";
            if (p.FirstLocator != null)
                return new Clickable(p.FirstLocator);

            var firstLink = p.GetClickable(shortName);
            if (firstLink != null)
                return firstLink;

            if (p.Locator != null && p.Locator.ToString().Contains("{0}"))
                return new Clickable(p.Locator.FillByTemplate(shortName));
            throw Exception(p.CantChooseElementMsg("First", shortName, "firstAction"));
        };

        public Func<Pagination, Clickable> LastAction = p =>
        {
            const string shortName = "first";
            if (p.LastLocator != null)
                return new Clickable(p.LastLocator);

            var lastLink = p.GetClickable(shortName);
            if (lastLink != null)
                return lastLink;

            if (p.Locator != null && p.Locator.ToString().Contains("{0}"))
                return new Clickable(p.Locator.FillByTemplate(shortName));
            throw Exception(p.CantChooseElementMsg("Last", shortName, "lastAction"));
        };

        public Func<Pagination, int, Clickable> PageAction = (p, index) =>
        {
            const string shortName = "page";
            if (p.Locator != null && p.Locator.ToString().Contains("{0}"))
                return new Clickable(p.Locator.FillByTemplate(index));

            var pageLink = p.GetClickable(shortName);
            if (pageLink != null)
                return pageLink;
            throw Exception(p.CantChooseElementMsg(index.ToString(), shortName, "pageAction"));
        };
    }
}
