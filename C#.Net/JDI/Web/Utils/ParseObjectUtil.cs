using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using Epam.JDI.Commons;
using Epam.JDI.Core;
using Epam.JDI.Core.Attributes;
using RestSharp.Extensions;
using static System.Int32;

namespace Epam.JDI.Web.Utils
{
    public static class ParseObjectUtil
    {
        public static Dictionary<string, string> ParseAsString(this string objString)
        {
            return ExceptionUtils.ActionWithException(() =>
            {
                if (objString == null)
                    return null;
                var result = new Dictionary<string, string>();
                var values = new List<string>();
                var i = 1;
                var str = objString;
                while (objString.IndexOf("#(#") > 0)
                {
                    values.Add(objString.Substring(objString.IndexOf("#(#") + 3, objString.IndexOf("#)#")));
                    str = objString.Replace("#\\(#.*#\\)#", "#VAL" + i++);
                }
                var fields = str.Split("#;#");
                fields.ForEach(field =>
                {
                    var splitField = field.Split("#:#");
                    if (splitField.Count == 2)
                        result.Add(splitField[0], ProcessValue(splitField[1], values));
                });
                return result;
            }, ex => $"Can't parse string '{objString}' to Object");
        }
        public static string ProcessValue(string input, IList<string> values)
        {
            if (input.Equals("#NULL#"))
                return null;
            return input.Matches("#VAL\\d*") 
                ? values[Parse(input.Substring(4)) - 1] 
                : input;
        }


        public static Dictionary<string, string> ToSetValue(this object obj)
        {
            return obj == null
                    ? new Dictionary<string, string>()
                    : ParseObjectAsString(PrintObject(obj));
        }
        private static string PrintObject(object obj)
        {
            var result = new List<string>();
            obj.GetFields().ForEach(field =>
            {
                var value = field.GetValue(obj);
                string strValue = null;
                if (value == null)
                    strValue = "#NULL#";
                else if (value is string)
                    strValue = (string) value;
                else if (value is IConvertible)
                    strValue = value.ToString();
                else if (ComplexAttribute.IsPresent(field) )
                    strValue = "#(#" + PrintObject(value) + "#)#";
                if (strValue != null)
                    result.Add($"{NameAttribute.GetElementName(field)}#:#{strValue}");
            });
            return result.Print("#;#");
        }

        private static Dictionary<string, string> ParseObjectAsString(string objString)
        {
            if (objString == null)
                return null;
            var result = new Dictionary<string, string>();
            var values = new List<string>();
            var i = 1;
            var str = objString;
            int from;
            while ((from = str.IndexOf("#(#")) > 0)
            {
                var to = str.IndexOf("#)#");
                values.Add(str.Substring(from + 3, to - from - 3));
                str = new Regex("#\\(#.*#\\)#").Replace(str, "#VAL" + i++);
            }
            var fields = str.Split("#;#");
            fields.ForEach(field =>
            {
                var splitField = field.Split("#:#");
                if (splitField.Count == 2)
                    result.Add(splitField[0], ProcessValue(splitField[1], values));
            });
            return result;
        }
    }
}
