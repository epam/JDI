﻿using System;
using JDI_Commons;
using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;
using RestSharp.Extensions;

namespace JDI_Web.Selenium.Elements.Common
{
    public class Link : ClickableText, ILink
    {
        public Link() : this(null) { }
        public Link(By byLocator = null, IWebElement webElement = null)
            : base(byLocator, webElement) { }

        protected Func<WebBaseElement, string> GetReferenceFunc =
          el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("href"), "");
        public string GetReference()
        {
            return Invoker.DoJActionResult("Get link reference", GetReferenceFunc, href => $"Get href of link '{href}'");
        }
        
        public string WaitReferenceContains(string text)
        {
            Func<WebBaseElement, Func<string>> textFunc = el => () => GetReferenceFunc(el);
            return Invoker.DoJActionResult(
                    $"Wait link contains '{text}'",
                    el => textFunc(el).GetByCondition(t => t.Contains(text))
                    );
        }

        public string WaitMatchReference(string regEx)
        {
            Func<WebBaseElement, Func<string>> textFunc = el => () => GetReferenceFunc(el);
            return Invoker.DoJActionResult(
                    $"Wait link match regex '{regEx}'",
                    el => textFunc(el).GetByCondition(t => t.Matches(regEx))
                    );
        }

        protected Func<WebBaseElement, string> GetTooltipFunc =
          el => el.WebAvatar.FindImmediately(() => el.WebElement.GetAttribute("title"), "");
        public string GetTooltip()
        {
            return Invoker.DoJActionResult("Get link tooltip", GetTooltipFunc, href => $"Get link tooltip '{href}'");
        }
    }
}
