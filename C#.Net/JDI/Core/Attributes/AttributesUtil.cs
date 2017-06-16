using System.Linq;
using System.Reflection;
using Epam.JDI.Core.Attributes.Functions;

namespace Epam.JDI.Core.Attributes
{
    public static class AttributesUtil
    {
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

        private static string SplitCamelCase(string camel)
        {
            string result = (camel.ElementAt(0) + "").ToUpper();
            for (int i = 1; i < camel.Length - 1; i++)
                result += ((IsCapital(camel.ElementAt(i)) && !IsCapital(camel.ElementAt(i - 1))) ? " " : "") + camel.ElementAt(i);
            return result + camel.ElementAt(camel.Length - 1);
        }

        private static bool IsCapital(char ch)
        {
            return 'A' < ch && ch < 'Z';
        }
    }
}
