using System;
using System.Reflection;

namespace Epam.JDI.Core.Attributes
{    
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FillFromFieldAttribute : Attribute
    {
        public readonly string FieldName;

        public FillFromFieldAttribute(string fieldName)
        {
            FieldName = fieldName;
        }

        public static string GetFieldName(FieldInfo field)
        {
            var fillFrom = field.GetCustomAttribute<FillFromFieldAttribute>(false);
            return fillFrom != null ? fillFrom.FieldName : "";
        }
    }
}
