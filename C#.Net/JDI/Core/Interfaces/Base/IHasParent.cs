using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Interfaces.Base
{
    public interface IHasParent
    {
        void SetParent(object parent);
        object GetParent();
    }
}
