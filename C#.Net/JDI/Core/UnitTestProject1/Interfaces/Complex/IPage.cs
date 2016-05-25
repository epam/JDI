using JDI_Core.Interfaces.Base;

namespace JDI_Core.Interfaces.Complex
{
    public interface IPage : IComposite
    {
        /**
         * Check that page opened
         */
         void CheckOpened();

        /**
         * Opens url specified for page
         */
        void Open();
    }
}
