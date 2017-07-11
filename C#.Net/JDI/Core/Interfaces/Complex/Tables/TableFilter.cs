using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Complex.Tables
{
    public class TableFilter
    {
        public String name;
        public String value;
        public CheckPageTypes type;

        public TableFilter(string template)
        {
            string[] split;
            
            if (Regex.IsMatch(template, "[^=]+\\*=[^=]*"))
            {
                split = template.Split(new[] { "\\*=" }, StringSplitOptions.None);
                name = split[0];
                value = split[1];
                type = CheckPageTypes.MATCH;
                return;
            }
            if (Regex.IsMatch(template, "[^=]+~=[^=]*"))
            {
                split = template.Split(new string[] { "~=" }, StringSplitOptions.None);
                name = split[0];
                value = split[1];
                type = CheckPageTypes.CONTAINS;
                return;
            }
            if (Regex.IsMatch(template, "[^=] +=[^=] * "))
            {
                split = template.Split(new string[] { "=" }, StringSplitOptions.None);
                name = split[0];
                value = split[1];
                type = CheckPageTypes.EQUAL;
                return;
            }
            throw new ArgumentException("Wrong searchCriteria for Cells: " + template);
        }
    }
}
