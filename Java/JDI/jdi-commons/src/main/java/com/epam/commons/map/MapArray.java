package com.epam.commons.map;
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

import com.epam.commons.LinqUtils;
import com.epam.commons.linqinterfaces.JActionTTEx;
import com.epam.commons.linqinterfaces.JFuncTREx;
import com.epam.commons.linqinterfaces.JFuncTTREx;
import com.epam.commons.pairs.Pair;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.TryCatchUtil.throwRuntimeException;
import static java.util.stream.Collectors.toList;

/**
 * Created by Roman_Iovlev on 6/3/2015.
 */
public class MapArray<K, V> implements Collection<Pair<K, V>>, Cloneable {
    public List<Pair<K, V>> pairs;

    public MapArray() {
        pairs = new CopyOnWriteArrayList<>();
    }

    public MapArray(K key, V value) {
        this();
        add(key, value);
    }

    public <T> MapArray(Collection<T> collection, JFuncTREx<T, K> key, JFuncTREx<T, V> value) {
        this();
        try {
            for (T t : collection)
                add(key.invoke(t), value.invoke(t));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }

    public MapArray(Collection<K> collection, JFuncTREx<K, V> value) {
        this();
        try {
            for (K k : collection)
                add(k, value.invoke(k));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }

    public <T> MapArray(T[] array, JFuncTREx<T, K> key, JFuncTREx<T, V> value) {
        this();
        try {
            for (T t : array)
                add(key.invoke(t), value.invoke(t));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }

    public MapArray(K[] array, JFuncTREx<K, V> value) {
        this();
        try {
        for (K k : array)
            add(k, value.invoke(k));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }

    public MapArray(int count, JFuncTREx<Integer, K> key, JFuncTREx<Integer, V> value) {
        this();
        try {
        for (int i = 0; i < count; i++)
            add(key.invoke(i), value.invoke(i));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }
    public MapArray(int count, JFuncTREx<Integer, Pair<K, V>> pairFunc) {
        this();
        try {
        for (int i = 0; i < count; i++)
            add(pairFunc.invoke(i));
        } catch (Exception ex) {
            throw new RuntimeException("Can't create MapArray"); }
    }

    public MapArray(MapArray<K, V> mapArray) {
        this();
        addAll(mapArray.stream().collect(toList()));
    }

    public MapArray(Object[][] objects) {
        this();
        add(objects);
    }
    public MapArray(List<K> keys, List<V> values) {
        this();
        if (keys == null || values == null ||
            keys.size() == 0 || keys.size() != values.size())
            throw new RuntimeException("keys and values count not equal");
        for (int i = 0; i < keys.size(); i++)
            add(keys.get(i), values.get(i));
    }

    public static <T> MapArray<Integer, T> toMapArray(Collection<T> collection) {
        MapArray<Integer, T> mapArray = new MapArray<>();
        int i = 0;
        for (T t : collection)
            mapArray.add(i++, t);
        return mapArray;
    }

    public static <T> MapArray<Integer, T> toMapArray(T[] array) {
        Set<T> mySet = new HashSet<>();
        Collections.addAll(mySet, array);
        return toMapArray(mySet);
    }

    public static <Key, Value> MapArray<Key, Value> toMapArray(Map<Key, Value> map) {
        MapArray<Key, Value> mapArray = new MapArray<>();
        for (Map.Entry<Key, Value> e : map.entrySet())
            mapArray.add(e.getKey(), e.getValue());
        return mapArray;
    }

    public <KResult, VResult> MapArray<KResult, VResult> toMapArray(
            JFuncTTREx<K, V, KResult> key, JFuncTTREx<K, V, VResult> value) {
        MapArray<KResult, VResult> result = new MapArray<>();
        try {
            for (Pair<K, V> pair : pairs)
                result.add(key.invoke(pair.key, pair.value), value.invoke(pair.key, pair.value));
        } catch (Exception ex) {
            throw new RuntimeException("Can't convert toMapArray"); }
        return result;
    }

    public <VResult> MapArray<K, VResult> toMapArray(JFuncTREx<V, VResult> value) {
        MapArray<K, VResult> result = new MapArray<>();
        try {
        for (Pair<K, V> pair : pairs)
            result.add(pair.key, value.invoke(pair.value));
        return result;
        } catch (Exception ex) {
            throw new RuntimeException("Can't convert toMapArray"); }
    }

    public Map<K, V> toMap() {
        return toMap(v -> v);
    }
    public <VResult> Map<K, VResult> toMap(JFuncTREx<V, VResult> value) {
        return toMap((k, v) -> k, (k,v) -> value.invoke(v));
    }
    public <KResult, VResult> Map<KResult, VResult> toMap(
            JFuncTTREx<K, V, KResult> key, JFuncTTREx<K, V, VResult> value) {
        Map<KResult, VResult> result = new HashMap<>();
        try {
            for (Pair<K, V> pair : pairs)
                result.put(key.invoke(pair.key, pair.value),
                        value.invoke(pair.key, pair.value));
        } catch (Exception ex) {
            throw new RuntimeException("Can't convert toMap"); }
        return result;
    }

    public boolean add(K key, V value) {
        if (hasKey(key))
            return false;
        pairs.add(new Pair<>(key, value));
        return true;
    }
    public MapArray<K, V> update(K key, V value) {
        if (hasKey(key))
            removeByKey(key);
        pairs.add(new Pair<>(key, value));
        return this;
    }


    public MapArray<K, V> update(K key, JFuncTREx<V, V> func) {
        V value = null;
        if (hasKey(key)) {
            value = get(key);
            removeByKey(key);
        }
        try {
            pairs.add(new Pair<>(key, func.invoke(value)));
        } catch (Exception ex) {
            throw new RuntimeException("Can't do update"); }
        return this;
    }

    public void add(Object[][] pairs) {
        for (Object[] pair : pairs)
            if (pair.length == 2)
                add((K) pair[0], (V) pair[1]);
    }

    public void addOrReplace(K key, V value) {
        if (hasKey(key))
            removeByKey(key);
        add(key, value);
    }

    public void addOrReplace(Object[][] pairs) {
        for (Object[] pair : pairs)
            if (pair.length == 2)
                addOrReplace((K) pair[0], (V) pair[1]);
    }

    private boolean hasKey(K key) {
        return keys().contains(key);
    }

    public boolean addFirst(K key, V value) {
        if (hasKey(key))
            return false;
        List<Pair<K, V>> result = new CopyOnWriteArrayList<>();
        result.add(new Pair<>(key, value));
        result.addAll(pairs);
        pairs = result;
        return true;
    }

    public V get(K key) {
        Pair<K, V> first = null;
        try {
            first = LinqUtils.first(pairs, pair -> pair.key.equals(key));
        } catch (Exception ignore) {
        }
        return (first != null) ? first.value : null;
    }

    public Pair<K, V> get(int i) {
        int index = i >= 0 ? i : pairs.size() + i;
        if (index < 0)
            return null;
        return (pairs.size() > index)
                ? pairs.get(index)
                : null;
    }

    public Pair<K, V> getFromEnd(int index) {
        return get(size() - index - 1);
    }

    public K key(int index) {
        return get(index).key;
    }

    public V value(int index) {
        return get(index).value;
    }

    public Collection<K> keys() {
        return LinqUtils.select(pairs, pair -> pair.key);
    }

    public Collection<V> values() {
        return LinqUtils.select(pairs, pair -> pair.value);
    }

    public Collection<V> values(JFuncTREx<V, Boolean> condition) {
        return LinqUtils.where(values(), condition);
    }

    public int size() {
        return pairs.size();
    }

    public int count() {
        return size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean any() {
        return size() > 0;
    }

    public Pair<K, V> first() {
        return get(0);
    }

    public Pair<K, V> last() {
        return getFromEnd(0);
    }

    public MapArray<K, V> revert() {
        List<Pair<K, V>> result = new CopyOnWriteArrayList<>();
        for (int i = size() - 1; i >= 0; i--)
            result.add(get(i));
        pairs = result;
        return this;
    }

    public boolean contains(Object o) {
        return values().contains(o);
    }

    public Iterator<Pair<K, V>> iterator() {
        return pairs.iterator();
    }

    public Object[] toArray() {
        return pairs.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return pairs.toArray(a);
    }

    public boolean add(Pair<K, V> kv) {
        return pairs.add(kv);
    }
/*
    public V remove(Object key) {
        boolean isRemoved = false;
        for (Object kv : pairs)
            if (kv.equals(key)) {
                V value = ((Pair<K,V>)kv).value;
                pairs.remove(kv);
                return value;
            }
        return null;
    }*/

    public boolean remove(Object o) {
        boolean isRemoved = false;
        for (Object kv : pairs)
            if (kv.equals(o)) {
                pairs.remove(kv);
                isRemoved = true;
            }
        return isRemoved;
    }

    public void removeByKey(K key) {
        pairs.remove(LinqUtils.firstIndex(pairs, pair -> pair.key.equals(key)));
    }

    public void removeAllValues(V value) {
        LinqUtils.where(pairs, p -> p.value.equals(value)).forEach(pairs::remove);
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends Pair<K, V>> c) {
        for (Pair<K, V> pair : c)
            add(pair);
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            if (!remove(o))
                return false;
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (Pair pair : pairs)
            if (!c.contains(pair))
                if (!remove(pair))
                    return false;
        return true;
    }

    public void clear() {
        pairs.clear();
    }

    @Override
    public String toString() {
        return print(LinqUtils.select(pairs, pair -> pair.key + ":" + pair.value));
    }

    @Override
    public MapArray<K, V> clone() {
        return new MapArray<>(this);
    }

    public MapArray<K, V> copy() {
        return clone();
    }

    public <T1> List<T1> select(JFuncTTREx<K, V, T1> func) {
        try {
            List<T1> result = new ArrayList<>();
            for (Pair<K,V> pair : pairs)
                result.add(func.invoke(pair.key, pair.value));
            return result;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return new ArrayList<>();
        }
    }

    public MapArray<K, V> filter(JFuncTTREx<K, V, Boolean> func) {
        return where(func);
    }
    public MapArray<K, V> where(JFuncTTREx<K, V, Boolean> func) {
        try {
            MapArray<K, V> result = new MapArray<>();
            for (Pair<K,V> pair : pairs)
                if (func.invoke(pair.key, pair.value))
                    result.add(pair);
            return result;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

    public V first(JFuncTTREx<K, V, Boolean> func) {
        try {
            for (Pair<K, V> pair : pairs)
                if (func.invoke(pair.key, pair.value))
                    return pair.value;
            return null;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

    public void foreach(JActionTTEx<K, V> action) {
        try {
            for (Pair<K, V> pair : pairs)
                action.invoke(pair.key, pair.value);
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
        }
    }

    public <R> List<R> selectMany(JFuncTTREx<K, V, List<R>> func) {
        try {
            List<R> result = new ArrayList<>();
            for (Pair<K, V> pair : pairs)
                result.addAll(func.invoke(pair.key, pair.value));
            return result;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

}