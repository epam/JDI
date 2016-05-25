using System;
using System.Collections.Generic;
using System.Linq;

namespace JDI_Commons.Pairs
{
    public static class PairsExtentions
    {
        public static Pairs ToPairs(this List<string> pairs, char separator)
        {
            var result = new Pairs();
            foreach (var splitted in pairs.Select(pair => pair.Split(separator)))
                result.Add(splitted[0], splitted[1]);
            return result;
        }

        public static Pairs ToPairs(this IEnumerable<IGrouping<string, string>> pairs)
        {
            return pairs.ToPairs<string,string>().ToPairs();
        }
        
        public static Pairs<TKey, TElement> ToPairs<TKey, TElement>(this IEnumerable<IGrouping<TKey, TElement>> pairs)
        {
            return pairs.Select(group => new Pair<TKey, TElement>(group.Key, group.FirstOrDefault())).ToPairs();
        }

        public static Pairs ToPairs(this IEnumerable<KeyValuePair<string, string>> pairs)
        {
            return pairs.ToPairs<string, string>().ToPairs();
        }

        public static Pairs ToPairs(this IEnumerable<Pair<string, string>> pairs)
        {
            var result = new Pairs();
            foreach (var pair in pairs)
                result.Add(pair.Value1, pair.Value2);
            return result;
        }

        public static Pairs ToPairs(this IEnumerable<Pair<string, string>> pairs, Dictionary<string,string> dict)
        {
            var result = new Pairs();
            foreach (var pair in pairs)
                result.Add(dict.ContainsKey(pair.Value1) ? dict[pair.Value1] : "", pair.Value2);
            return result;
        }

        public static Pairs ToPairs(this IEnumerable<KeyValuePair<string, string>> pairs, Dictionary<string, string> dict)
        {
            var result = new Pairs();
            pairs.ToPairs().ForEach(pair => result.Add(dict.ContainsKey(pair.Value1) ? dict[pair.Value1] : "", pair.Value2));
            return result;
        }

        public static Pairs<TKey, TValue> ToPairs<TKey, TValue>(this IEnumerable<KeyValuePair<TKey, TValue>> pairs)
        {
            return pairs.Select(element => new Pair<TKey, TValue>(element.Key, element.Value)).ToPairs();
        }

        public static Pairs<TKey, TValue> ToPairs<TKey, TValue>(this IEnumerable<Pair<TKey, TValue>> pairs)
        {
            var result = new Pairs<TKey, TValue>();
            foreach (var pair in pairs)
                result.Add(pair.Value1, pair.Value2);
            return result;
        }

        public static Pairs<TRValue1, TRValue2> ToPairs<TValue1, TValue2, TRValue1, TRValue2>(this IEnumerable<Pair<TValue1, TValue2>> pairs, 
            Func<TValue1, TRValue1> selectorValue1, 
            Func<TValue2 ,TRValue2> selectorValue2)
        {
            var result = new Pairs<TRValue1, TRValue2>();
            foreach (var pair in pairs)
                result.Add(selectorValue1.Invoke(pair.Value1), selectorValue2.Invoke(pair.Value2));
            return result;
        }

        public static Pairs<TValue1, TValue2> ToPairs<T, TValue1, TValue2>(this IEnumerable<T> list, 
            Func<T, TValue1> selectorValue1, Func<T, TValue2> selectorValue2)
        {
            var pairs = new Pairs<TValue1, TValue2>();
            foreach (var element in list)
                pairs.Add(selectorValue1(element), selectorValue2(element));
            return pairs;
        }

        public static string Print(this IEnumerable<Pair<string, string>> collection, string separator = "; ", string pairFormat = "{0}: {1}")
        {
            return (collection != null) ? string.Join(separator, collection.Select(pair => string.Format(pairFormat, pair.Value1, pair.Value2))) : "";
        }

        public static Pairs AddToPairs(this Pairs<string, string> pairs, string key, string value)
        {
            return pairs.AddToPairs<string, string>(key, value).ToPairs();
        }

        public static Pairs<TKey, TValue> AddToPairs<TKey, TValue>(this Pairs<TKey, TValue> pairs, TKey key, TValue value)
        {
            pairs.Add(key, value);
            return pairs;
        }
    }
}
