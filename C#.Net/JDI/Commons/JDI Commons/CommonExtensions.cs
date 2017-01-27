using System;
using System.Collections.Generic;
using System.Linq;

namespace JDI_Commons
{
    public static class CommonExtensions
    {
        public static void ForEach<T>(this IEnumerable<T> enumerable, Action<T> action)
        {
            foreach (var element in enumerable)
                action(element);
        }

        public static string FromNewLine(this string s)
        {
            return " " + Environment.NewLine + s;
        }

        public static string LineBreak(this string s)
        {
            return s + " " + Environment.NewLine;
        }

        public static string CutTestData(this string str)
        {
            var index = str.IndexOf('(');
            return index > 0
                ? str.Substring(0, index)
                : str;
        }

        public static string Print(this IEnumerable<string> list, string separator = ", ", string format = "{0}")
        {
            return list != null ? string.Join(separator, list.Select(el => string.Format(format, el))) : "";
        }

        public static string ToString(this IEnumerable<string> list, string separator = ", ", string format = "{0}")
        {
            return list != null ? string.Join(separator, list.Select(el => string.Format(format, el))) : "";
        }

        public static string Print<TValue>(this IEnumerable<KeyValuePair<string, TValue>> collection,
            string separator = "; ", string pairFormat = "{0}: {1}")
        {
            return collection != null
                ? string.Join(separator, collection.Select(pair => string.Format(pairFormat, pair.Key, pair.Value)))
                : "";
        }


        public static object GetFieldByName(this object obj, string fieldName)
        {
            var fieldsQueue = new Queue<string>(fieldName.Split('.'));
            var result = obj;
            while (fieldsQueue.Any() && result != null)
            {
                var fieldsName = fieldsQueue.Dequeue();
                var fieldInfo = result.GetType().GetField(fieldsName);
                if (fieldInfo != null)
                {
                    result = fieldInfo.GetValue(result);
                    continue;
                }
                var propInfo = result.GetType().GetProperty(fieldsName);
                result = propInfo?.GetValue(result);
            }
            return result;
        }


        public static int FirstIndex<T>(this IList<T> list, Func<T, bool> func)
        {
            if (list == null)
                return -1;
            for (var i = 0; i < list.Count; i++)
                if (func(list[i]))
                    return i;
            return -1;
        }

        public static IList<string> Split(this string s, string separator)
        {
            return s.Split(new[] {separator}, StringSplitOptions.None);
        }

        public static IList<string> SplitTrim(this string s, string separator)
        {
            return s.Split(new[] {separator}, StringSplitOptions.RemoveEmptyEntries);
        }

        public static IList<T> ListCopy<T>(this IList<T> list, int from = 0, int to = 0)
        {
            if (from*to < 0)
                throw new Exception($"from and to should have same sign ({from}, {to})");
            if (from < 0)
                from = list.Count + from - 1;
            if (to <= 0)
                to = list.Count + to - 1;
            var result = new List<T>();
            for (var i = from; i <= to; i++)
                result.Add(list[i]);
            return result;
        }

        public static Dictionary<T, T1> ToDictionary<T, T1>(this IEnumerable<KeyValuePair<T, T1>> pairs)
        {
            return pairs.ToDictionary(el => el.Key, el => el.Value);
        }
    }
}
