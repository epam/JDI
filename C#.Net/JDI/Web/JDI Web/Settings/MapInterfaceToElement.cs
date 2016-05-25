using System;
using System.Collections.Generic;
using JDI_Commons;

namespace JDI_Web.Settings
{
    public static class MapInterfaceToElement
    {
        private static Dictionary<Type, Type> _map = new Dictionary<Type, Type>();

        public static void Init(Dictionary<Type, Type> map)
        {
            _map = map;
        }
        public static void Update(Dictionary<Type, Type> map)
        {
            map.ForEach(pair => map[pair.Key] = pair.Value);
        }

        public static Type ClassFromInterface(Type clazz)
        {
            return _map[clazz];
        }
    }
}
