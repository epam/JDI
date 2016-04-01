using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace Epam.JDI.Commons
{
    public static class ReflectionUtils
    {
        public static List<FieldInfo> GetFields(Type type)
        {
            return type.GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Instance).ToList();
        }

        public static List<FieldInfo> StaticFields(Type type)
        {
            return type.GetFields(BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static).ToList();
        }

        public static bool ContainsType(this Type[] types, FieldInfo field)
        {
            return types.Contains(field.FieldType);
        }
        public static bool ContainsFieldType(this Type[] types, FieldInfo field)
        {
            return types.Any(type => type.IsAssignableFrom(field.FieldType));
        }
        public static List<FieldInfo> GetFields(this object obj, params Type[] types)
        {
            var fields = GetFields(obj.GetType());
            return types.Length == 0
                ? fields
                : fields.Where(types.ContainsFieldType).ToList();
        }
        public static FieldInfo GetFirstField(this object obj, params Type[] types)
        {
            var fields = GetFields(obj.GetType());
            return types.Length == 0
                ? fields[0]
                : fields.FirstOrDefault(types.ContainsFieldType);
        }

        public static T GetFirstValue<T>(this object obj, params Type[] types)
        {
            var fields = GetFields(obj.GetType());
            return (T) (types.Length == 0
                ? fields[0].GetValue(obj)
                : fields.FirstOrDefault(types.ContainsFieldType)?.GetValue(obj));
        }
        public static string GetClassName(this object obj)
        {
            return obj?.GetType().Name ?? "NULL Class";
        }
        
    }
}
