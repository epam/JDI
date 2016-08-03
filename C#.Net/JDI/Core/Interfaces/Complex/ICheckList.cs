﻿using System;
using Epam.JDI.Core.Interfaces.Base;

namespace Epam.JDI.Core.Interfaces.Complex
{
    public interface ICheckList : ICheckList<IConvertible> { }
    public interface ICheckList<in TEnum> : IMultiSelector<TEnum>
        where TEnum : IConvertible
    {
    }
}
