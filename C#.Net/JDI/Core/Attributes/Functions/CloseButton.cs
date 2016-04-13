using System;
using System.Reflection;

namespace Epam.JDI.Core.Attributes.Functions
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class CloseButtonAttribute : Attribute
    {
        private readonly string _pageName;

        public CloseButtonAttribute(string pageName = "")
        {
            _pageName = pageName;
        }
        public static string Handler(FieldInfo field)
        {
            var attr = field.GetCustomAttribute<CloseButtonAttribute>(false);
            return attr?._pageName;
        }
    }
}

