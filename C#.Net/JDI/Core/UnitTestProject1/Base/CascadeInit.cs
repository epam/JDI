using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using JDI_Commons;
using JDI_Core.Interfaces.Base;

namespace JDI_Core.Base
{
    public abstract class CascadeInit
    {
        protected abstract void SetElement(object parent, Type parentType, FieldInfo field, string driverName);


        protected Type[] Decorators = {typeof (IBaseElement), typeof(IList)};

        public void InitElements(object parent, string driverName)
        {
            SetFieldsForInit(parent, parent.GetType().GetFieldsList(), parent.GetType(), driverName);
        }

        private void SetFieldsForInit(object parent, List<FieldInfo> fields, Type parentType, string driverName)
        {
            fields.Where(field => Decorators.ToList().Any(type => type.IsAssignableFrom(field.FieldType))).ToList()
                .ForEach(field => SetElement(parent, parentType, field, driverName));
        }


        public void InitStaticPages(Type parentType, string driverName)
        {
            SetFieldsForInit(null, parentType.StaticFields(), parentType, driverName);
        }

        public T InitPages<T>(Type site, string driverName) where T : Application
        {
            var instance = (T) Activator.CreateInstance(site);
            instance.DriverName = driverName;
            InitElements(instance, driverName);
            return instance;
        }
    }
}
