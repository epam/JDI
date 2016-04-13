using System;
using System.Reflection;

namespace Epam.JDI.Core.Attributes.Functions
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class CancelButtonAttribute : Attribute
    {
        private readonly string _pageName;

        public CancelButtonAttribute(string pageName = "")
        {
            _pageName = pageName;
        }
        public static string Handler(FieldInfo field)
        {
            var attr = field.GetCustomAttribute<CancelButtonAttribute>(false);
            return attr?._pageName;
        }
    }
}

