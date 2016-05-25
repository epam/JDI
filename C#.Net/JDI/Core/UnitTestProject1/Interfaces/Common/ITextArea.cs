namespace JDI_Core.Interfaces.Common
{
    public interface ITextArea : ITextField
    {
        /**
         * @param textLines Specify text lines (clear textArea before
         *                  Clear textarea and Input several lines of text in textarea
         */
        //TODO[JDIAction]
        void InputLines(params string[] textLines);

        /**
         * @param textLine Specify text to add new line (without clearing previous)
         *                 Add text in textarea from new line
         */
        //TODO[JDIAction]
        void AddNewLine(string textLine);

        /**
         * @return Get lines of text in textarea
         */
        //TODO[JDIAction]
        string[] GetLines();
    }
}
