using System;
using Epam.JDI.Core.Logging;
using OpenQA.Selenium;
using System.Reflection;
using Epam.JDI.Core.Attributes.Functions;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Web.Selenium.Elements.Pageobjects.Annotations;
using Epam.JDI.Web.Selenium.Elements.apiInteract;
using Epam.JDI.Web.Selenium.Elements.Actions;
using Epam.JDI.Web.Selenium.Elements.Common;

namespace Epam.JDI.Core
{
    public abstract class BaseElement : IBaseElement
    {
        public static bool createFreeInstance = false;
        public static ActionScenrios actionScenrios = new ActionScenrios();
        
        public static Action<string, Action<string>> DoActionRule = (text, action) => {
            if (text == null) return;
            action.Invoke(text);
        };
        
        public Functions function = Functions.None;
        public GetElementModule avatar;

        protected string parentTypeName = "";

        private string name;
        private string varName;
        private string typeName;

        public BaseElement()
        {
            throw new NotImplementedException();
            //TODO this(By.Id("EMPTY"));
        }

        public void SetFunction(Functions function)
        {
            this.function = function;
        }

        public void DoAction(String actionName, Action action, LogLevels logLevels = LogLevels.INFO)
        {
        }

        public void DoActionResult<TResult>(String actionName, Func<TResult> action, Func<TResult, String> logResult = null, LogLevels logLevels = LogLevels.INFO)
        {
        }
        
        
        public void setName(FieldInfo field)
        {
            this.name = WebAnnotationsUtil.GetElementName(field);
            this.varName = field.Name;
        }

        public void setName(string name)
        {
            this.name = name;
        }
        
        public void SetTypeName(string typeName)
        {
            this.typeName = typeName;
        }
        public void setParentName(string parentName)
        {
            parentTypeName = parentName;
        }
        /**
         * @return Get WebDriver associated with Element
          */
        //TODO old code: public WebDriver GetDriver() { ... }
        
        /**
        * @return Get Element’s locator
        */
        public By getLocator()
        {
            throw new NotImplementedException();//TODO getLocator()
        }
        public GetElementModule GetAvatar()
        {
            return avatar;
        }


        IAvatar IBaseElement.GetAvatar()
        {
            throw new NotImplementedException();//TODO remove IBaseElement.GetAvatar() or GetElementModule GetAvatar()
        }

        public BaseElement setAvatar(GetElementModule avatar)
        {
            this.avatar = avatar;
            return this;
        }

        public BaseElement setAvatar(By byLocator, GetElementModule avatar)
        {
            throw new NotImplementedException();//TODO setAvatar(By byLocator, GetElementModule avatar)
        }



        protected string GetTypeName()
        {
            //TODO return (typeName != null) ? typeName : getClass().getSimpleName();
            return (typeName != null) ? typeName : GetType().Name;
        }
        public string GetName()
        {
            return name != null ? name : GetTypeName();
        }

        public void SetName(FieldInfo field)
        {
            this.name = WebAnnotationsUtil.GetElementName(field);
            this.varName = field.Name;
        }
        public void SetName(string name)
        {
            this.name = name;
        }

        protected string GetParentName()
        {
            return parentTypeName;
        }

        public void SetParentName(string parentName)
        {
            parentTypeName = parentName;
        }

        public void setWaitTimeout(long mSeconds)
        {
            throw new NotImplementedException();//TODO setWaitTimeout(long mSeconds)
        }

        public void restoreWaitTimeout()
        {
            throw new NotImplementedException();//TODO restoreWaitTimeout()
        }

        public void logAction(String actionName, LogLevels level)
        {
            throw new NotImplementedException();//TODO logAction(String actionName, LogLevels level)
        }


        public void LogAction(string actionName)
        {
            throw new NotImplementedException();//TODO LogAction(string actionName)
        }

    }
}
