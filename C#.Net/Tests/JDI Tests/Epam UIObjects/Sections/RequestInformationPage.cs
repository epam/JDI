using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.Tests.Scenarios.Enums;
using Epam.Tests.Scenarios.Page_Objects.Sections;

namespace Epam.Tests.Scenarios.Page_Objects.Pages
{
    public class RequestInformationPage : WebPage
    {
        [FindBy(Css = "textarea[name*=\"user_comment_how_hear_about\"]")]
        public TextArea MessageArea;
    }
}
