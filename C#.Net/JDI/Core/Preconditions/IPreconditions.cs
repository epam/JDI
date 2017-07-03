using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Core.Preconditions
{
    public interface IPreconditions
    {
        bool CheckAction();
        void MoveToAction();
    }
}
