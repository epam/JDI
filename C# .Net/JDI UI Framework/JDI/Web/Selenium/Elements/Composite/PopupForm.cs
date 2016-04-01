using System;
using Epam.JDI.Core.Interfaces.Complex;

namespace Epam.JDI.Web.Selenium.Elements.Composite
{
    public class PopupForm<T> : Form<T>, IPopup
    {
        public string Text => "";

        public string WaitText(string text)
        {
            throw new NotImplementedException();
        }

        public string WaitMatchText(string regEx)
        {
            throw new NotImplementedException();
        }
        public void Cancel()
        {
            throw new NotImplementedException();
        }
        public void Close()
        {
            throw new NotImplementedException();
        }
        public void Ok()
        {
            throw new NotImplementedException();
        }
    }
}
