using System;
using System.Collections.Generic;
using JDI_Core.Interfaces.Base;

namespace JDI_Core.Interfaces.Complex
{
    public interface ISelector : ISelector<IConvertible> { }

    public interface ISelector<in TEnum> : IBaseElement, ISetValue
        where TEnum : IConvertible
    {
        /**
     * @param name Specify name using string
     *             Select WebElement with name (use text) from list
     */
        //TODO[JDIAction]
        void Select(string name);

        /**
         * @param name Specify name using enum
         *             Select WebElement with name (use enum) from list
         */
        //TODO[JDIAction]
        void Select(TEnum name);

        /**
         * @param index Specify digit to select
         *              Select WebElement with name (use index) from list
         */
        //TODO[JDIAction]
        void Select(int index);

        /**
         * @return Get name of the selected WebElement
         */
        //TODO[JDIAction]
        string Selected();
        /**
         * @return Get index of the selected WebElement
         */
        //TODO[JDIAction]
        int SelectedIndex();

        /**
         * @param name Specify name using string
         * @return Is option selected?
         */
        //TODO[JDIAction]
        bool Selected(string name);

        /**
         * @param name Specify name using enum
         * @return Is option selected?
         */
        //TODO[JDIAction]
        bool Selected(TEnum name);

        /**
         * @param name Specify name using string
         * Wait while option (from text) is selected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitSelected(string name);

        /**
         * @param name Specify name using enum
         * Wait while option (from enum) is selected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitSelected(TEnum name);

        /**
         * @return Get labels of all options
         */
        //TODO[JDIAction]
        IList<string> Options { get; }

        //TODO GetName() was default
        IList<string> Names { get; }

        //TODO GetValues() was default 
        IList<string> Values { get; }

        /**
         * @return Get all options labels in one string separated with “; ”
         */
        //TODO[JDIAction]
        //TODO GetOptionsAsText() was default
        string OptionsAsText { get; }
    }
}
