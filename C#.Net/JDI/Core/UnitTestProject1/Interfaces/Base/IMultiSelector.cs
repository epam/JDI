using System;
using System.Collections.Generic;

namespace JDI_Core.Interfaces.Base
{
    public interface IMultiSelector<in TEnum>: IBaseElement, ISetValue
        where TEnum  : IConvertible
    {
        /**
    * @param names Specify names
    *              Select options with name (use text) from list (change their state selected/deselected)
    */
        //TODO[JDIAction]
        void Select(params string[] names);

        /**
         * @param names Specify names
         *              Select options with name (use enum) from list (change their state selected/deselected)
         */
        //TODO[JDIAction]
        void Select(params TEnum[] names);

        /**
         * @param indexes Specify indexes
         *                Select options with name (use index) from list (change their state selected/deselected)
         */
        //TODO[JDIAction]
        void Select(params int[] indexes);

        /**
         * @param names Specify names
         *              Check only specified options (use text) from list (all other options unchecked)
         */
        //TODO[JDIAction]
        void Check(params string[] names);

        /**
         * @param names Specify names
         *              Check only specified options (use enum) from list (all other options unchecked)
         */
        //TODO[JDIAction]
        void Check(params TEnum[] names);

        /**
         * @param indexes Specify indexes
         *                Check only specified options (use index) from list (all other options unchecked)
         */
        //TODO[JDIAction]
        void Check(params int[] indexes);

        /**
         * @param names Specify names
         *              Uncheck only specified options (use text) from list (all other options checked)
         */
        //TODO[JDIAction]
        void Uncheck(params string[] names);

        /**
         * @param names Specify names
         *              Uncheck only specified options (use enum) from list (all other options checked)
         */
        //TODO[JDIAction]
        void Uncheck(params TEnum[] names);

        /**
         * @param indexes Specify indexes
         *                Uncheck only specified options (use index) from list (all other options checked)
         */
        //TODO[JDIAction]
        void Uncheck(params int[] indexes);

        /**
         * @return Get names of checked options
         */
        //TODO[JDIAction]
        IList<string> AreSelected();

        /**
         * @param names Specify names
         * Wait while all options with names (use text) selected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitSelected(params string[] names);

        /**
         * @param names Specify names
         * Wait while all options with names (use enum) selected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitSelected(params TEnum[] names);

        /**
         * @return Get names of unchecked options
         */
        //TODO[JDIAction]
        IList<string> AreDeselected();

        /**
         * @param names Specify names
         * Wait while all options with names (use text) deselected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitDeselected(params string[] names);

        /**
         * @param names Specify names
         * Wait while all options with names (use enum) deselected. Return false if this not happens
         */
        //TODO[JDIAction]
        void WaitDeselected(params TEnum[] names);

        /**
         * @return Get labels of all options
         */
        //TODO[JDIAction]
        IList<string> Options { get; }

        /**
         * @return Get labels of all options
         */
        //TODO[JDIAction]
        //TODO GetNames() was default
        IList<string> Names { get; }

        /**
         * @return Get labels of all options
         */
        //TODO[JDIAction]
        //TODO  GetValues() was default
        IList<string> Values { get; }

        /**
         * @return Get all options labels in one string separated with “; ”
         */
        //TODO[JDIAction]
        //TODO GetOptionsAsText() was default
        string OptionsAsText { get; }

        /**
         * Set all options checked
         */
        //TODO[JDIAction]
        void CheckAll();

        /**
         * Set all options checked
         */
        //TODO[JDIAction]
        //TODO SelectAll() was default
        void SelectAll();

        /**
         * Set all options unchecked
         */
        //TODO[JDIAction]
        void Clear();

        /**
         * Set all options unchecked
         */
        //TODO[JDIAction]
        //TODO UncheckAll() was default
        void UncheckAll();
    }
}
