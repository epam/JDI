using System.Linq;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Complex.Table;
using static System.Int32;
using static Epam.JDI.Core.Settings.JDISettings;

namespace JDI_Web.Attributes.Objects
{
    public class FillFromAnnotationRules
    {
        public static void SetUpTable(Table table, JTableAttribute jTable)
        {
            table.SetUp(jTable.Root.ByLocator, jTable.Cell.ByLocator,
                    jTable.Row.ByLocator, jTable.Column.ByLocator, jTable.Footer.ByLocator,
                    jTable.ColStartIndex, jTable.RowStartIndex);

            if (jTable.Header != null)
                table.ColumnHeaders = jTable.Header;
            if (jTable.RowsHeader != null)
                table.RowHeaders = jTable.RowsHeader;

            if (jTable.Height > 0)
                table.SetColumnsCount(jTable.Height);
            if (jTable.Width > 0)
                table.SetRowsCount(jTable.Width);
            if (!jTable.Size.Equals(""))
            {
                var split = jTable.Size.Split('x');
                if (split.Length == 1)
                    split = jTable.Size.Split('X');
                if (split.Length != 2)
                    throw Exception("Can't setup Table from attribute. Bad size: " + jTable.Size);
                table.SetColumnsCount(Parse(split[0]));
                table.SetRowsCount(Parse(split[1]));
            }
            table.HeaderType = jTable.HeaderType;
            table.UseCache(jTable.UseCache);
        }

        public static void SetUpDropdown(Dropdown dropdown, JDropdownAttribute jDropdown)
        {
            dropdown.SetUp(jDropdown.Root.ByLocator, jDropdown.Value.ByLocator,
                    jDropdown.List.ByLocator, jDropdown.Expand.ByLocator,
                    jDropdown.ElementByName.ByLocator);
        }
        public static void SetUpMenu(Menu menu, JMenuAttribute jMenu)
        {
            menu.SetUp(jMenu.LevelLocators.Select(findby => findby.ByLocator).ToList());
            if (!jMenu.Separator.Equals(""))
                menu.UseSeparator(jMenu.Separator);
        }
    }
}
