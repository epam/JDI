﻿using Epam.JDI.Core.Interfaces.Common;
using JDI_Web.Attributes;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_UIWebTests.UIObjects.Sections
{
    public class JdiPaginator : Pagination
    {
        [FindBy(Css = "[class=next]  a")]
        public new IButton Next;

        [FindBy(Css = "[class=prev]  a")]
        public IButton Prev;

        [FindBy(Css = "[class=first] a")]
        public new IButton First;

        [FindBy(Css = "[class=last]  a")]
        public new IButton Last;

        [FindBy(Css = ".uui-pagination li")]
        public IButton Page;
    }
}
