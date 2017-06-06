package com.epam.commons;

import java.lang.reflect.Field;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.PrintUtils.print;

/**
 * Created by Roman_Iovlev on 6/4/2017.
 */
public class DataClass {
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + print(LinqUtils.select(getClass().getDeclaredFields(),
            f -> f.getName() + ":" + f.get(this)), ";") + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        try {
            Field[] otherFields = getClass().getDeclaredFields();
            for (Field f : getClass().getDeclaredFields()) {
                Field fOther = first(otherFields, fo -> fo.getName().equals(f.getName()));
                if (f.get(this) == null && fOther.get(o) == null)
                    continue;
                if (f.get(this) != null && fOther.get(o) == null ||
                    f.get(this) == null && fOther.get(o) != null ||
                    !f.get(this).equals(fOther.get(o)))
                    return false;
                }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Field f : getClass().getDeclaredFields())
            try {
            result += 31 * result + f.get(this).hashCode();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        return result;
    }
}
