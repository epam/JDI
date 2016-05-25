using System;
using System.Reflection;

namespace JDI_Core.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class MoveToAttribute : Attribute
    {
        private readonly string _pageName;

        public MoveToAttribute(string pageName = "")
        {
            _pageName = pageName;
        }
        public static string Handler(FieldInfo field)
        {
            var attr = field.GetCustomAttribute<MoveToAttribute>(false);
            return attr?._pageName;
        }
    }
}

