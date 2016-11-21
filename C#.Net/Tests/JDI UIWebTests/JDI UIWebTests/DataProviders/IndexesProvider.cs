using System.Collections.Generic;

namespace JDI_UIWebTests.DataProviders
{
    class IndexesProvider
    {
        public static IEnumerable<int> Indexes
        {
            get
            {
                yield return -10;
                yield return 0;
                yield return 10;
            }
        } 
    }
}
