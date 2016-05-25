namespace JDI_Web.Selenium.Elements.Complex.table
{
    public class Column : RowColumn
    {
        public Column(int num = -1, string name = null) : base(num, name) { }

        public static Column column(int num)
        {
            return new Column(num);
        }

        public static Column column(string name)
        {
            return new Column(name: name);
        }
    }
}
