using System;
using System.Linq;
using System.Reflection;

namespace JDI_Core.Attributes
{
    [AttributeUsage(AttributeTargets.All, Inherited = false)]
    public class NameAttribute : Attribute
    {
        private readonly string _name;

        public NameAttribute(string name) { _name = name; }

        public static string GetName(FieldInfo field)
        {
            var name = field.GetCustomAttribute<NameAttribute>(false);
            return name != null ? name._name : "";
        }

        public static string GetName(object obj)
        {
            var name = obj.GetType().GetCustomAttribute<NameAttribute>(false);
            return name != null ? name._name : "";
        }

        public static string GetElementName(FieldInfo field)
        {
            var name = GetName(field);
            return string.IsNullOrEmpty(name) ? SplitCamelCase(field.Name) : name;
        }

        private static string SplitCamelCase(string camel)
        {
            var result = camel.ToUpper().FirstOrDefault().ToString();
            for (var i = 1; i < camel.Length - 1; i++)
                result += (IsCapital(camel[i]) && !IsCapital(camel[i - 1]) ? " " : "") + camel[i];
            return result + camel[camel.Length - 1];
        }
        private static bool IsCapital(char ch)
        {
            return 'A' < ch && ch < 'Z';
        }
    }
}
