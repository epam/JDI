using System;
using System.Reflection;

namespace Epam.JDI.Web.Attributes
{
    [AttributeUsage(AttributeTargets.All, Inherited = false)]
    public class SiteAttribute : Attribute
    {
        public string Domain { get; set; }
        public bool UseCache { get; set; }
        public bool IsMain { get; set; }
        public bool DemoMode { get; set; }
        public bool ScreenshotAlert { get; set; }

        public SiteAttribute() { IsMain = true; UseCache = true; }

        public static SiteAttribute Get(FieldInfo field)
        {
            return field.GetCustomAttribute<SiteAttribute>(false);
        }

        public static SiteAttribute Get(object obj)
        {
            return obj.GetType().GetCustomAttribute<SiteAttribute>(false);
        }
        public static SiteAttribute Get(Type type)
        {
            return type.GetCustomAttribute<SiteAttribute>(false);
        }
    }
}
