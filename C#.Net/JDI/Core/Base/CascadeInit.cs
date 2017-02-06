using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Commons;
using static Epam.JDI.Core.Attributes.AttributesUtil;
using static Epam.JDI.Core.ExceptionUtils;

namespace Epam.JDI.Core.Base
{
    public abstract class CascadeInit
    {
        protected Type[] Decorators = {typeof(IBaseElement), typeof(IList)};
        protected abstract Type[] StopTypes { get; }

        public void InitElements(object parent, string driverName)
        {
            SetFieldsForInit(parent, parent.GetFields(Decorators, StopTypes), parent.GetType(), driverName);
        }

        public void InitStaticPages(Type parentType, string driverName)
        {
            SetFieldsForInit(null,
                parentType.StaticFields().GetFields(Decorators), parentType, driverName);
        }

        private void SetFieldsForInit(object parent, List<FieldInfo> fields, Type parentType, string driverName)
        {
            fields.Where(field => Decorators.ToList().Any(type => type.IsAssignableFrom(field.FieldType))).ToList()
                .ForEach(field => SetElement(parent, parentType, field, driverName));
        }

        public T InitPages<T>(Type site, string driverName) where T : Application
        {
            var instance = (T) Activator.CreateInstance(site);
            instance.DriverName = driverName;
            InitElements(instance, driverName);
            return instance;
        }

        protected abstract void FillPageFromAnnotation(FieldInfo field, IBaseElement instance, Type parentType);
        protected abstract IBaseElement FillInstance(IBaseElement instance, FieldInfo field);

        protected IBaseElement GetInstancePage(object parent, FieldInfo field, Type type, Type parentType)
        {
            var instance = (IBaseElement) (field.GetValue(parent)
                                           ?? Activator.CreateInstance(type));
            FillPageFromAnnotation(field, instance, parentType);
            return instance;
        }

        protected IBaseElement GetInstanceElement(object parent, Type type, Type parentType, FieldInfo field,
            string driverName)
        {
            var instance = CreateChildFromFieldStatic(parent, parentType, field, type, driverName);
            instance.SetFunction(GetFunction(field));
            return instance;
        }

        protected virtual IBaseElement SpecificAction(IBaseElement instance, FieldInfo field, object parent, Type type)
        {
            return instance;
        }

        protected virtual IBaseElement FillFromJDIAttribute(IBaseElement instance, FieldInfo field)
        {
            return instance;
        }

        private IBaseElement CreateChildFromFieldStatic(object parent, Type parentClass, FieldInfo field, Type type,
            string driverName)
        {
            var instance = (IBaseElement) field.GetValue(parent);
            if (instance == null)
                instance = ActionWithException(
                    () => GetElementInstance(field, driverName, parent),
                    ex =>
                        $"Can't create child for parent '{parentClass.Name}' with type '{field.FieldType.Name}'. Exception: {ex}");
            else FillInstance(instance, field);
            instance.Parent = parent;
            instance = FillFromJDIAttribute(instance, field);
            instance = SpecificAction(instance, field, parent, type);
            return instance;
        }

        protected void SetElement(object parent, Type parentType, FieldInfo field, string driverName)
        {
            ActionWithException(() =>
            {
                var type = field.FieldType;
                var instance = typeof(IPage).IsAssignableFrom(type)
                    ? GetInstancePage(parent, field, type, parentType)
                    : GetInstanceElement(parent, type, parentType, field, driverName);
                instance.SetName(field);
                instance.Avatar.DriverName = driverName;
                instance.TypeName = type.Name;
                instance.Parent = parent;
                field.SetValue(parent, instance);
                if (typeof(IComposite).IsAssignableFrom(type))
                    InitElements(instance, driverName);
            },
                ex =>
                    $"Error in SetElement for field '{field.Name}' with parent '{parentType?.Name ?? "NULL Class" + ex.FromNewLine()}'");
        }

        protected abstract IBaseElement GetElementsRules(FieldInfo field, string driverName, Type type, string fieldName);

        protected IBaseElement GetElementInstance(FieldInfo field, string driverName, object parent)
        {
            var type = field.FieldType;
            var fieldName = field.Name;
            return ActionWithException(() => GetElementsRules(field, driverName, type, fieldName),
                ex =>
                    $"Error in GetElementInstance for field '{fieldName}'{(parent != null ? "in " + parent.GetClassName() : "")} with type '{type.Name + ex.FromNewLine()}'");
        }
    }
}
