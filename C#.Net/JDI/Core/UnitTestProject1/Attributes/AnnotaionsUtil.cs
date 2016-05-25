using System;
using System.Linq;
using System.Reflection;
using Epam.JDI.Core.Attributes.Functions;

namespace Epam.JDI.Core.Attributes
{
    public  class AnnotaionsUtil
    {
        public static String GetElementName(FieldInfo field)
        {
            var name = NameAttribute.GetName(field);
            return String.IsNullOrEmpty(name)
                ? name
                : SplitCamelCase(field.Name);
        }

        public static Functions.Functions GetFunction(FieldInfo field)
        {
            if (field.GetCustomAttribute<OkButtonAttribute>(false) != null)
                return Functions.Functions.Ok;
            if (field.GetCustomAttribute<CloseButtonAttribute>(false) != null)
                return Functions.Functions.Close;
            if (field.GetCustomAttribute<CancelButtonAttribute>(false) != null)
                return Functions.Functions.Cancel;
            return Functions.Functions.None;
        }

        private static String SplitCamelCase(String camel)
        {
            var result = camel.ToUpper().First().ToString();
            for (int i = 1; i < camel.Length - 1; i++)
                result += (IsCapital(camel[i]) && !IsCapital(camel[i - 1]) ? " " : "") + camel[i];
            return result + camel[camel.Length - 1];
        }
        private static Boolean IsCapital(char ch)
        {
            return 'A' < ch && ch < 'Z';
        }
    }


}
