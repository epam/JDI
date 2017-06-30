using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace JDI_Commons
{
    public static class ReflectionUtils
    {
        public static List<FieldInfo> GetFieldsList(this Type type)
        {
            return type.GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Instance).ToList();
        }

        public static List<FieldInfo> StaticFields(this Type type)
        {
            return type.GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static).ToList();
        }

        public static object Value(this FieldInfo f, Object parent)
        {
            try
            {
                return f.GetValue(parent);
            }
            catch
            {
                if (parent == null)
                    return null;
                throw;
            }
        }

        public static bool ContainsType(this Type[] types, FieldInfo field)
        {
            return types.Contains(field.FieldType);
        }
        public static bool ContainsFieldType(this Type[] types, FieldInfo field)
        {
            return types.Any(type => type.IsAssignableFrom(field.FieldType));
        }
        public static List<FieldInfo> GetFieldsDeep(this Type type, params Type[] types)
        {
            if (types.Contains(type))
                return new List<FieldInfo>();
            var result = type.GetFieldsList();
            result.AddRange(GetFieldsDeep(type.BaseType, types));
            return result;
        }

        public static List<FieldInfo> GetFields(this object obj, params Type[] types)
        {
            return GetFields(obj, types, typeof(object));
        }
        public static List<FieldInfo> GetFields(this object obj, Type[] types, params Type[] stopTypes)
        {
            return GetFields(GetFieldsDeep(obj.GetType(), stopTypes), types);
        }

        public static List<FieldInfo> GetFields(this List<FieldInfo> fields, Type[] types)
        {
            return types == null || types.Length == 0
                ? fields
                : fields.Where(field => types.Any(t => t.IsAssignableFrom(field.FieldType))).ToList();
        }
        public static FieldInfo GetFirstField(this object obj, params Type[] types)
        {
            var fields = obj.GetType().GetFieldsList();
            return types.Length == 0
                ? fields[0]
                : fields.FirstOrDefault(types.ContainsFieldType);
        }

        public static T GetFirstValue<T>(this object obj, params Type[] types)
        {
            var fields = obj.GetType().GetFieldsList();
            return (T) (types.Length == 0
                ? fields[0].GetValue(obj)
                : fields.FirstOrDefault(types.ContainsFieldType)?.GetValue(obj));
        }
        public static string GetClassName(this object obj)
        {
            return obj?.GetType().Name ?? "NULL Class";
        }

        public static object ParseOrDefault(this string value, Func<string, object> parseAction, object defaultValue)
        {
            try { return parseAction(value); }
            catch { return defaultValue; }
        }

        public static object ConvertStringToType(this string value, FieldInfo field)
        {
            var type = field.FieldType;
            if (type == typeof(string) || value == null)
                return value;
            if (type == typeof(byte))
                return value.ParseOrDefault(s => byte.Parse(s), 0);
            if (type == typeof(int))
                return value.ParseOrDefault(s => int.Parse(s), -1);
            if (type == typeof(float))
                return value.ParseOrDefault(s => float.Parse(s), -1f);
            if (type == typeof(bool))
                return value.ParseOrDefault(s => bool.Parse(s), false);
            if (type == typeof(decimal))
                return value.ParseOrDefault(s => decimal.Parse(s), -1m);
            if (type == typeof(long))
                return value.ParseOrDefault(s => long.Parse(s), -1);
            if (type == typeof(byte?))
                return value.ParseOrDefault(s => byte.Parse(s), null);
            if (type == typeof(int?))
                return value.ParseOrDefault(s => int.Parse(s), null);
            if (type == typeof(float?))
                return value.ParseOrDefault(s => float.Parse(s), null);
            if (type == typeof(bool?))
                return value.ParseOrDefault(s => bool.Parse(s), null);
            if (type == typeof(decimal?))
                return value.ParseOrDefault(s => decimal.Parse(s), null);
            if (type == typeof(long?))
                return value.ParseOrDefault(s => long.Parse(s), null);
            throw new Exception($"Can't parse field {field.Name} type is unsupported");
        }

    }
}
