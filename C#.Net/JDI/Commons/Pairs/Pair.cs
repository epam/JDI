using System;
using System.Collections.Generic;

namespace Epam.JDI.Commons.Pairs
{
    public class Pair : Pair<string,string> { public Pair(string value1, string value2) : base(value1, value2) {} }

    public class Pair<TValue1, TValue2> : IEquatable<Pair<TValue1, TValue2>>
    {
        public bool Equals(Pair<TValue1, TValue2> other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;
            return EqualityComparer<TValue1>.Default.Equals(Value1, other.Value1) && EqualityComparer<TValue2>.Default.Equals(Value2, other.Value2);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return (EqualityComparer<TValue1>.Default.GetHashCode(Value1)*397) ^ EqualityComparer<TValue2>.Default.GetHashCode(Value2);
            }
        }

        public TValue1 Value1;
        public TValue2 Value2;

        public Pair(TValue1 value1, TValue2 value2)
        {
            Value1 = value1;
            Value2 = value2;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Pair<TValue1, TValue2>) obj);
        }
    }
}
