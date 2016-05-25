using System.Linq;
using JDI_Commons;
using JDI_Core.Attributes.Functions;
using JDI_Core.Interfaces.Common;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Common;
using static JDI_Core.Settings.JDISettings;

namespace JDI_Web.Selenium.Attributes
{
    public class GetElementClass
    {
        private readonly WebBaseElement _element;

        public GetElementClass(WebBaseElement element)
        {
            _element = element;
        }

        public static bool NamesEqual(string name1, string name2)
        {
            return name1.ToLower().Trim('_', ' ').Equals(name2.ToLower().Trim('_', ' '));
        }

        public Button GetButton(string buttonName)
        {
            var fields = _element.GetFields(typeof(IButton));
            switch (fields.Count)
            {
                case 0:
                    throw Exception($"Can't find ny buttons on form {ToString()}'");
                case 1:
                    return (Button)fields[0].GetValue(_element);
                default:
                    var buttons = fields.Select(f => (Button)f.GetValue(_element)).ToList();
                    var button = buttons.FirstOrDefault(b => NamesEqual(ToButton(b.Name), ToButton(buttonName)));
                    if (button == null)
                        throw Exception($"Can't find button '{buttonName}' for Element '{ToString()}'." +
                                        $"(Found following buttons: {buttons.Select(el => el.Name).Print()}).".FromNewLine());
                    return button;
            }
        }

        private string ToButton(string buttonName)
        {
            return buttonName.Contains("button") ? buttonName : buttonName + "button";
        }

        public Button GetButton(Functions funcName)
        {
            var fields = _element.GetFields(typeof(IButton));
            if (fields.Count == 1)
                return (Button) fields[0].GetValue(_element);
            var buttons = fields.Select(f => (Button) f.GetValue(_element)).ToList();
            var button = buttons.FirstOrDefault(b => b.Function.Equals(funcName));
            if (button != null) return button;
            var name = funcName.ToString();
            var buttonName = name.ToLower().Contains("button") ? name : name + "button";
            button = buttons.FirstOrDefault(b => NamesEqual(b.Name, buttonName));
            if (button == null)
                throw Exception($"Can't find button '{name}' for Element '{ToString()}'");
            return button;
        }

        public Textbox GetTextElement()
        {
            var textField = this.GetFirstField(typeof(Textbox), typeof(IText));
            if (textField == null)
                throw Exception($"Can't find Text Element '{ToString()}'");
            return (Textbox) textField.GetValue(_element);
        }
    }
}
