using System;
using System.Collections;
using System.Collections.Generic;
using System.Reflection;
using Epam.JDI.Core.Base;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Complex;
using JDI_Commons;
using JDI_Web.Attributes;
using JDI_Web.Attributes.Objects;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.APIInteract;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using JDI_Web.Selenium.Elements.Composite;
using JDI_Web.Settings;
using OpenQA.Selenium;
using RestSharp.Extensions;
using static Epam.JDI.Core.Settings.JDIData;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_Web.Attributes.Objects.FillFromAnnotationRules;
using EUtils = Epam.JDI.Core.ExceptionUtils;

namespace JDI_Web.Selenium.Base
{
    public class WebCascadeInit : CascadeInit
    {
        protected override Type[] StopTypes => new []
            {
                typeof(object),
                typeof(WebPage),
                typeof(Section),
                typeof(WebElement)
            };
        
        protected override void FillPageFromAnnotation(FieldInfo field, IBaseElement instance, Type parentType)
        {
            var pageAttribute = field.GetAttribute<PageAttribute>();
            pageAttribute?.FillPage((WebPage)instance, parentType);
        }

        protected override IBaseElement FillInstance(IBaseElement instance, FieldInfo field)
        {
            var element = (WebBaseElement) instance;
            if (!element.HasLocator)
                element.Avatar = new GetElementModule(element, GetNewLocator(field));
            return element;
        }
        protected override IBaseElement FillFromJDIAttribute(IBaseElement instance, FieldInfo field)
        {
            var element = (WebBaseElement) instance;
            FillFromAnnotation(element, field);
            return element;
        }

        protected override IBaseElement SpecificAction(IBaseElement instance, FieldInfo field, object parent, Type type)
        {
            var element = (WebBaseElement) instance;
            if (parent != null && type == null) return element;
            var frameBy = FrameAttribute.GetFrame(field);
            if (frameBy != null)
                element.WebAvatar.FrameLocator = frameBy;
            By template;
            var form = element.Parent as Form;
            if (form != null && !element.HasLocator
                && (template = form.LocatorTemplate) != null)
                element.WebAvatar.ByLocator = template.FillByTemplate(field.Name);
            return element;
        }
        protected override IBaseElement GetElementsRules(FieldInfo field, string driverName, Type type, string fieldName)
        {
            var newLocator = GetNewLocator(field);
            WebBaseElement instance = null;
            if (type == typeof(List<>))
                throw Exception($"Can't init element {fieldName} with type 'List<>'. Please use 'IList<>' or 'Elements<>' instead");
            if (typeof(IList).IsAssignableFrom(type))
            {
                var elementClass = type.GetGenericArguments()[0];
                if (elementClass != null)
                    instance = (WebBaseElement)Activator.CreateInstance(typeof(Elements<>)
                        .MakeGenericType(elementClass));
            }
            else
            {
                if (type.IsInterface)
                    type = MapInterfaceToElement.ClassFromInterface(type);
                if (type != null)
                {
                    instance = (WebBaseElement)Activator.CreateInstance(type);
                    if (newLocator != null)
                        instance.WebAvatar.ByLocator = newLocator;
                }
            }
            if (instance == null)
                throw Exception("Unknown interface: " + type +
                                ". Add relation interface -> class in VIElement.InterfaceTypeMap");
            instance.Avatar.DriverName = driverName;
            return instance;
        }

        protected By GetNewLocator(FieldInfo field)
        {
            return EUtils.ActionWithException(() => GetNewLocatorFromField(field),
                ex => $"Error in get locator for type '{field.Name + ex.FromNewLine()}'");
        }
        protected By GetNewLocatorFromField(FieldInfo field)
        {
            By byLocator = null;
            var locatorGroup = APP_VERSION;
            if (locatorGroup == null)
                return FindByAttribute.Locator(field) ?? field.GetFindsBy();
            var jFindBy = field.GetAttribute<JFindByAttribute>();
            if (jFindBy != null && locatorGroup.Equals(jFindBy.Group))
                byLocator = jFindBy.ByLocator;
            return byLocator ?? (FindByAttribute.Locator(field) ?? field.GetFindsBy());
        }

        private static void FillFromAnnotation(WebBaseElement instance, FieldInfo field)
        {
            SetUpTableFromAnnotation(instance, field);
            SetUpMenuFromAnnotation(instance, field);
            SetUpDropdownFromAnnotation(instance, field);
        }

        private static void SetUpTableFromAnnotation(WebBaseElement instance, FieldInfo field)
        {
            var jTable = field.GetAttribute<JTableAttribute>();
            if (jTable == null || !typeof(ITable).IsAssignableFrom(field.FieldType))
                return;
            SetUpTable((Table)instance, jTable);
        }

        private static void SetUpMenuFromAnnotation(WebBaseElement instance, FieldInfo field)
        {
            var jDropdown = field.GetAttribute<JDropdownAttribute>();
            if (jDropdown == null || !typeof(IDropDown).IsAssignableFrom(field.FieldType))
                return;
            SetUpDropdown((Dropdown)instance, jDropdown);
        }
        private static void SetUpDropdownFromAnnotation(WebBaseElement instance, FieldInfo field)
        {
            var jMenu = field.GetAttribute<JMenuAttribute>();
            if (jMenu == null || !typeof(IMenu).IsAssignableFrom(field.FieldType))
                return;
            SetUpMenu((Menu)instance, jMenu);
        }
        
    }
}
