using System.Reflection;
using JDI_Core.Attributes.Functions;

namespace JDI_Core.Attributes
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
