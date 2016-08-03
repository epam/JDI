using System;

namespace JDI_Web.Attributes.Objects
{
    [AttributeUsage(AttributeTargets.All, Inherited = false)]
    public class JMenuAttribute : Attribute
    {
        public FindByAttribute[] LevelLocators = null;
        public string Separator = "";
    }
}
