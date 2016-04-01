using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface IPopup : IText, IComposite
    {
        /**
         * Click on Button marked with annotation @OkButton or named "okButton"
         */
        //TODO[JDIAction]
         void Ok();

        /**
         * Click on Button marked with annotation @CancelButton or named "cancelButton"
         */
        //TODO[JDIAction]
        void Cancel();

        /**
         * Click on Button marked with annotation @CloseButton or named "closeButton"
         */
        //TODO[JDIAction]
        void Close();
    }
}
