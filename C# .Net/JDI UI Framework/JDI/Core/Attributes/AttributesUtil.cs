using System;
using System.Linq;
using System.Reflection;
using Epam.JDI.Core.Attributes.Functions;

namespace Epam.JDI.Core.Attributes
{
    public static class AttributesUtil
    {
        public static Functions.Functions GetFunction(FieldInfo field)
        {
            if (field.GetCustomAttribute<OkButtonAttribute>(false) != null)
                return Functions.Functions.Ok;
            if (field.GetCustomAttribute<CloseButtonAttribute>(false) != null)
                return Functions.Functions.Close;
            if (field.GetCustomAttribute<CancelButtonAttribute>(false) != null)
                return Functions.Functions.Cancel;
            return Functions.Functions.None;
        }
    }


}
