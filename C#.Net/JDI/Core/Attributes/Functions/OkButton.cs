using System;
using System.Reflection;

namespace Epam.JDI.Core.Attributes.Functions
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class OkButtonAttribute : Attribute
    {
        private readonly string _pageName;

        public OkButtonAttribute(string pageName = "")
        {
            _pageName = pageName;
        }
        public static string Handler(FieldInfo field)
        {
            var attr = field.GetCustomAttribute<OkButtonAttribute>(false);
            return attr?._pageName;
        }
    }
}

