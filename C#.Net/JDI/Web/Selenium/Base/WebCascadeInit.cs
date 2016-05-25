using System;
using System.Collections;
using System.Reflection;
using Epam.JDI.Commons;
using Epam.JDI.Core.Base;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Web.Attributes;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Composite;
using Epam.JDI.Web.Settings;
using NUnit.Framework;
using OpenQA.Selenium;
using RestSharp.Extensions;
using static Epam.JDI.Core.ExceptionUtils;
using static Epam.JDI.Core.Attributes.AttributesUtil;
using static Epam.JDI.Core.Settings.JDIData;
using static Epam.JDI.Core.Settings.JDISettings;

namespace Epam.JDI.Web.Selenium.Base
{
    public class WebCascadeInit : CascadeInit
    {
        protected override void SetElement(object parent, Type parentType, FieldInfo field, string driverName)
        {
            ActionWithException(() =>
            {
                var type = field.FieldType;
                var instance = typeof(IPage).IsAssignableFrom(type)
                    ? GetInstancePage(parent, field, type, parentType)
                    : GetInstanceElement(parent, parentType, field, driverName);
                instance.SetName(field);
                instance.Avatar.DriverName = driverName;
                instance.TypeName = type.Name;
                instance.Parent = parent;
                field.SetValue(parent, instance);
                if (typeof(IComposite).IsAssignableFrom(type))
                    InitElements(instance, driverName);
            }, ex => $"Error in setElement for field '{field.Name}' with parent '{parentType?.Name ?? "NULL Class" + ex.FromNewLine()}'");
        }

        private static IBaseElement GetInstancePage(object parent, FieldInfo field, Type type, Type parentType)
        {
            var instance = (IBaseElement) field.GetValue(parent) ?? (IBaseElement) Activator.CreateInstance(type);
            var pageAttribute = field.GetAttribute<PageAttribute>();
            pageAttribute?.FillPage((WebPage) instance, parentType);
            return instance;
        }

        private IBaseElement GetInstanceElement(object parent, Type parentType, FieldInfo field,
            string driverName)
        {
            var instance = CreateChildFromFieldStatic(parent, parentType, field, driverName);
            instance.SetFunction(GetFunction(field));
            return instance;
        }

        private IBaseElement CreateChildFromFieldStatic(object parent, Type parentClass, FieldInfo field,
            string driverName)
        {
            var instance = (WebBaseElement) field.GetValue(parent);
            if (instance == null)
                instance = ActionWithException(
                    () => GetElementInstance(field, driverName), 
                    ex => $"Can't create child for parent '{parentClass.Name}' with type '{field.FieldType.Name}'. Exception: {ex}");
            else if (instance.Locator == null)
                instance.WebAvatar.ByLocator = GetNewLocator(field);
            instance.Frame = FrameAttribute.GetFrame(field);
            return instance;
        }

        private WebBaseElement GetElementInstance(FieldInfo field, string driverName)
        {
            var type = field.FieldType;
            var fieldName = field.Name;
            var newLocator = GetNewLocator(field);
            return ActionWithException(() => 
            {
                WebBaseElement instance = null;
                if (type == typeof(List))
                    throw Exception($"Can't init element {fieldName} with type 'List<>'. Please use 'IList<>' or 'Elements<>' instead");
                if (typeof(IList).IsAssignableFrom(type))
                {
                    var elementClass = type.GetGenericArguments()[0];
                    if (elementClass != null)
                        instance = (WebBaseElement) Activator.CreateInstance(typeof(Elements<>)
                            .MakeGenericType(elementClass));
                }
                else  {
                    if (type.IsInterface)
                        type = MapInterfaceToElement.ClassFromInterface(type);
                    if (type != null)
                    {
                        instance = (WebBaseElement) Activator.CreateInstance(type);
                        instance.WebAvatar.ByLocator = newLocator;
                    }
                }
                if (instance == null)
                    throw Exception("Unknown interface: " + type +
                                    ". Add relation interface -> class in VIElement.InterfaceTypeMap");
                instance.Avatar.DriverName = driverName;
                return instance;
            }, ex => $"Error in getElementInstance for field {fieldName}' with type '{type.Name + ex.FromNewLine()}'");
        }

        private static By GetNewLocator(FieldInfo field)
        {
            return ActionWithException(() => 
            {
                var locatorGroup = AppVersion;
                if (locatorGroup != null)
                {
                    string groupName;
                    By byLocator;
                    JFindByAttribute.Get(field, out byLocator, out groupName);
                    if (groupName != null && locatorGroup.Equals(groupName) && byLocator != null)
                        return byLocator;
                }
                return FindByAttribute.FindsByLocator(field) ?? FindByAttribute.Locator(field);
            }, ex => $"Error in get locator for type '{field.Name + ex.FromNewLine()}'");
        }

    }
}
