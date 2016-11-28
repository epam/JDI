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
import com.epam.commons.pairs.Pair;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public <T> MapArray(Collection<T> collection, Function<T, K> key, Function<T, V> value) {
        this();
        for (T t : collection)
            add(key.apply(t), value.apply(t));
    }

    public MapArray(Collection<K> collection, Function<K, V> value) {
        this();
        for (K k : collection)
            add(k, value.apply(k));
    }

    public <T> MapArray(T[] array, Function<T, K> key, Function<T, V> value) {
        this();
        for (T t : array)
            add(key.apply(t), value.apply(t));
    }

    public MapArray(K[] array, Function<K, V> value) {
        this();
        for (K k : array)
            add(k, value.apply(k));
    }

    public MapArray(int count, Function<Integer, K> key, Function<Integer, V> value) {
        this();
        for (int i = 0; i < count; i++)
            add(key.apply(i), value.apply(i));
    }

    public MapArray(MapArray<K, V> mapArray) {
        this();
        addAll(mapArray.stream().collect(toList()));
    }

    public MapArray(Object[][] objects) {
        this();
        add(objects);
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
            BiFunction<K, V, KResult> key, BiFunction<K, V, VResult> value) {
        MapArray<KResult, VResult> result = new MapArray<>();
        for (Pair<K, V> pair : pairs)
            result.add(key.apply(pair.key, pair.value), value.apply(pair.key, pair.value));
        return result;
    }

    public <VResult> MapArray<K, VResult> toMapArray(Function<V, VResult> value) {
        MapArray<K, VResult> result = new MapArray<>();
        for (Pair<K, V> pair : pairs)
            result.add(pair.key, value.apply(pair.value));
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


    public MapArray<K, V> update(K key, Function<V, V> func) {
        V value = null;
        if (hasKey(key)) {
            value = get(key);
            removeByKey(key);
        }
        pairs.add(new Pair<>(key, func.apply(value)));
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

    /*
        public V get(Object oKey) {
            K key = null;
            try {
                key = (K)oKey;
            } catch (Exception ex) { throwRuntimeException(new Exception("Can't do get in MapArray. Key have wrong type")); }
            return get(key);
        }*/
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

    public Collection<V> values(Function<V, Boolean> condition) {
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

    public <T1> List<T1> select(BiFunction<K, V, T1> func) {
        try {
            return pairs.stream()
                    .map(pair -> func.apply(pair.key, pair.value))
                    .collect(toList());
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return new ArrayList<>();
        }
    }

    public MapArray<K, V> where(BiFunction<K, V, Boolean> func) {
        try {
            return pairs.stream().filter(pair -> func.apply(pair.key, pair.value))
                    .collect(Collectors.toCollection(MapArray::new));
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

    public V first(BiFunction<K, V, Boolean> func) {
        try {
            for (Pair<K, V> pair : pairs)
                if (func.apply(pair.key, pair.value))
                    return pair.value;
            return null;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

    public void foreach(BiConsumer<K, V> action) {
        try {
            for (Pair<K, V> pair : pairs)
                action.accept(pair.key, pair.value);
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
        }
    }

    public <R> List<R> selectMany(BiFunction<K, V, List<R>> func) {
        try {
            List<R> result = new ArrayList<>();
            for (Pair<K, V> pair : pairs)
                result.addAll(func.apply(pair.key, pair.value));
            return result;
        } catch (Exception ignore) {
            throwRuntimeException(ignore);
            return null;
        }
    }

}