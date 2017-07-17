package com.epam.commons;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */

import com.epam.commons.linqinterfaces.JActionTEx;
import com.epam.commons.linqinterfaces.JFuncTREx;
import com.epam.commons.linqinterfaces.JFuncTTREx;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * Created by roman.i on 30.09.2014.
 */
public final class LinqUtils {
    private LinqUtils() {
    }

    public static <T> List<T> copyList(Collection<T> list) {
        List<T> result = new ArrayList<>();
        for (T el : list)
            result.add(el);
        return result;
    }

    public static <T, TR> List<TR> select(Collection<T> list, JFuncTREx<T, TR> func) {
        if (list == null)
            throw new RuntimeException("Can't do select. Collection is Null");
        try {
            List<TR> result = new CopyOnWriteArrayList<>();
            for (T el : list)
                result.add(func.invoke(el));
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do select. Exception: " + ex.getMessage());
        }
    }

    public static <T, TR> List<TR> select(T[] array, JFuncTREx<T, TR> func) {
        return select(asList(array), func);
    }

    public static <K, V, R> List<R> selectMap(Map<K, V> map, JFuncTREx<Map.Entry<K, V>, R> func) {
        if (map == null)
            throw new RuntimeException("Can't do selectMap. Collection is Null");
        try {
            List<R> result = new CopyOnWriteArrayList<>();
            for (Map.Entry<K,V> el : map.entrySet())
                result.add(func.invoke(el));
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do select. Exception: " + ex.getMessage());
        }
    }

    public static <K, V, TR> Map<K, TR> select(Map<K, V> map, JFuncTREx<V, TR> func) {
        if (map == null)
            throw new RuntimeException("Can't do select. Collection is Null");
        try {
            Map<K, TR> result = new HashMap<>();
            for (Map.Entry<K, V> el : map.entrySet())
                result.put(el.getKey(), func.invoke(el.getValue()));
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do select. Exception: " + ex.getMessage());
        }
    }

    public static <K, V, TR> List<TR> toList(Map<K, V> map, JFuncTTREx<K, V, TR> func) {
        if (map == null)
            throw new RuntimeException("Can't do select. Collection is Null");
        try {
            List<TR> result = new CopyOnWriteArrayList<>();
            for (Map.Entry<K,V> el : map.entrySet())
                result.add(func.invoke(el.getKey(), el.getValue()));
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do select. Exception: " + ex.getMessage());
        }
    }

    public static <T> List<T> where(Collection<T> list, JFuncTREx<T, Boolean> func) {
        if (list == null)
            throw new RuntimeException("Can't do where. Collection is Null");
        try {
            List<T> result = new ArrayList<>();
            for (T el : list)
                if (func.invoke(el))
                    result.add(el);
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do where. Exception: " + ex.getMessage());
        }
    }

    public static <T> List<T> where(T[] list, JFuncTREx<T, Boolean> func) {
        return where(asList(list), func);
    }

    public static <K, V> Map<K, V> where(Map<K, V> map, JFuncTREx<Map.Entry<K, V>, Boolean> func) {
        if (map == null)
            throw new RuntimeException("Can't do where. Collection is Null");
        try {
            Map<K, V> result = new HashMap<>();
            for (Map.Entry<K,V> el : map.entrySet())
                if (func.invoke(el))
                    result.put(el.getKey(), el.getValue());
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do where. Exception: " + ex.getMessage());
        }
    }

    public static <T> void foreach(Collection<T> list, JActionTEx<T> action) {
        if (list == null)
            throw new RuntimeException("Can't do foreach. Collection is Null");
        try {
            for (T el : list)
                action.invoke(el);
        } catch (Exception ex) {
            throw new RuntimeException("Can't do foreach. Exception: " + ex.getMessage());
        }
    }

    public static <T> void foreach(T[] list, JActionTEx<T> action) {
        foreach(asList(list), action);
    }

    public static <K, V> void foreach(Map<K, V> map, JActionTEx<Map.Entry<K, V>> action) {
        if (map == null)
            throw new RuntimeException("Can't do foreach. Collection is Null");
        try {
            for (Map.Entry e : map.entrySet())
                action.invoke(e);
        } catch (Exception ex) {
            throw new RuntimeException("Can't do foreach. Exception: " + ex.getMessage());
        }
    }

    public static <T> T first(Collection<T> list) {
        if (list == null || list.size() == 0)
            throw new RuntimeException("Can't do first. Collection is Null");
        return list.iterator().next();
    }

    public static <T> T first(T[] list) {
        return first(asList(list));
    }

    public static <K, V> V first(Map<K, V> map) {
        if (map == null || map.size() == 0)
            throw new RuntimeException("Can't do first. Collection is Null");
        return map.entrySet().iterator().next().getValue();
    }

    public static <T> T first(Collection<T> list, JFuncTREx<T, Boolean> func) {
        if (list == null || list.size() == 0)
            return null;
        try {
            for (T el : list)
                if (func.invoke(el))
                    return el;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do first. Exception: " + ex.getMessage());
        }
        return null;
    }

    public static <T> boolean any(Collection<T> list, JFuncTREx<T, Boolean> func) {
        return first(list, func) != null;
    }
    public static <T> boolean any(T[] list, JFuncTREx<T, Boolean> func) {
        return first(list, func) != null;
    }
    public static <T> int firstIndex(List<T> list, JFuncTREx<T, Boolean> func) {
        if (list == null)
            throw new RuntimeException("Can't get firstIndex. Collection is Null");
        try {
            for (int i = 0; i < list.size(); i++)
                if (func.invoke(list.get(i)))
                    return i;
        } catch (Exception ex) {
            throw new RuntimeException("Can't get firstIndex. Exception: " + ex.getMessage());
        }
        return -1;
    }

    public static <T> int firstIndex(T[] array, JFuncTREx<T, Boolean> func) {
        try {
            if (array == null)
                return -1;
            for (int i = 0; i < array.length; i++)
                if (func.invoke(array[i]))
                    return i;
            return -1;
        } catch (Exception ex) {
            throw new RuntimeException("Can't get firstIndex. Exception: " + ex.getMessage());
        }
    }

    public static <T> T first(T[] list, JFuncTREx<T, Boolean> func) {
        return first(asList(list), func);
    }

    public static <K, V> V first(Map<K, V> map, JFuncTREx<K, Boolean> func) {
        if (map == null)
            throw new RuntimeException("Can't do first. Collection is Null");
        try {
            for (Map.Entry<K, V> el : map.entrySet())
                if (func.invoke(el.getKey()))
                    return el.getValue();
        } catch (Exception ex) {
            throw new RuntimeException("Can't do first. Exception: " + ex.getMessage());
        }
        return null;
    }

    public static <K, V> V first(MapArray<K, V> map, JFuncTREx<K, Boolean> func) {
        if (map == null)
            throw new RuntimeException("Can't do first. Collection is Null");
        try {
            for (Pair<K, V> pair : map.pairs)
                if (func.invoke(pair.key))
                    return pair.value;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do first. Exception: " + ex.getMessage());
        }
        return null;
    }

    public static <T> T last(Collection<T> list) {
        if (list == null)
            throw new RuntimeException("Can't do last. Collection is Null");
        T result = null;
        for (T el : list)
            result = el;
        return result;
    }

    public static <T> T last(T[] list) {
        return last(asList(list));
    }

    public static <T> T last(Collection<T> list, JFuncTREx<T, Boolean> func) {
        if (list == null)
            throw new RuntimeException("Can't do last. Collection is Null");
        T result = null;
        try {
            for (T el : list)
                if (func.invoke(el))
                    result = el;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do last. Exception: " + ex.getMessage());
        }
        return result;
    }

    public static <T> T last(T[] list, JFuncTREx<T, Boolean> func) {
        return last(asList(list), func);
    }

    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null)
            throw new RuntimeException("Can't do toStringArray. Collection is Null");
        return collection.toArray(new String[collection.size()]);
    }

    public static int[] toIntArray(Collection<Integer> collection) {
        if (collection == null)
            throw new RuntimeException("Can't do toIntArray. Collection is Null");
        int[] result = new int[collection.size()];
        int i = 0;
        for (Integer el : collection)
            result[i++] = el;
        return result;
    }

    public static int getIndex(String[] array, String value) {
        if (array == null)
            throw new RuntimeException("Can't do index. Collection is Null");
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(value))
                return i;
        return -1;
    }

    public static int getIndex(List<String> list, String value) {
        if (list == null)
            throw new RuntimeException("Can't do index. Collection is Null");
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(value))
                return i;
        return -1;
    }

    public static <T> List<T> listCopy(List<T> list, int from) {
        return listCopy(list, from, list.size() - 1);
    }

    public static <T> List<T> listCopy(List<T> list, int from, int to) {
        if (from*to < 0)
            throw new RuntimeException(format("from and to should have same sign (%s, %s)", from, to));
        if (from < 0)
            from = list.size() + from - 1;
        if (to < 0)
            to = list.size() + to - 1;
        List<T> result = new ArrayList<>();
        for (int i = from; i <= to; i++)
            result.add(list.get(i));
        return result;
    }

    public static <T> List<T> listCopyUntil(List<T> list, int to) {
        return listCopy(list, 0, to);
    }

    public static <T> List<T> selectMany(List<T> list, JFuncTREx<T, List<T>> func) {
        try {
            List<T> result = new ArrayList<>();
            for (T el : list)
                for (T element : func.invoke(el))
                    result.add(element);
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do selectMany. Exception: " + ex.getMessage());
        }
    }

    public static <T> List<T> selectManyArray(List<T> list, JFuncTREx<T, T[]> func) {
        try {
                List<T> result = new ArrayList<>();
            for (T el : list)
                for (T element : func.invoke(el))
                    result.add(element);
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't do selectMany. Exception: " + ex.getMessage());
    }
    }

}