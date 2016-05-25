using System;
using System.Reflection;

namespace JDI_Core.Attributes
{
    [AttributeUsage(AttributeTargets.Class, Inherited = false)]
    public class ComplexAttribute : Attribute
    {
        public static bool IsPresent(FieldInfo field)
        {
            var attribute = field.GetCustomAttribute<ComplexAttribute>(false);
            return attribute == null;
        }
    }
}
