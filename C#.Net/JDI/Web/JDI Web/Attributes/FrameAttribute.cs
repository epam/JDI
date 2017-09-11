using System;
using System.Reflection;
using OpenQA.Selenium;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FrameAttribute : Attribute
    {
        private By _frameLocator;
        public string Id { set => _frameLocator = By.Id(value);
            get => "";
        }
        public string Name { set => _frameLocator = By.Name(value);
            get => "";
        }
        public string Class { set => _frameLocator = By.ClassName(value);
            get => "";
        }
        public string Css { set => _frameLocator = By.CssSelector(value);
            get => "";
        }
        public string XPath { set => _frameLocator = By.XPath(value);
            get => "";
        }
        public string Tag { set => _frameLocator = By.TagName(value);
            get => "";
        }
        public string LinkText { set => _frameLocator = By.LinkText(value);
            get => "";
        }
        public string PartialLinkText { set => _frameLocator = By.PartialLinkText(value);
            get => "";
        }

        public FrameAttribute() { }
        public FrameAttribute(string frameId) { _frameLocator = By.Id(frameId); }

        public static By GetFrame(FieldInfo field)
        {
            var frame = field.GetCustomAttribute<FrameAttribute>(false);
            return frame?._frameLocator;
        }
    }
}
