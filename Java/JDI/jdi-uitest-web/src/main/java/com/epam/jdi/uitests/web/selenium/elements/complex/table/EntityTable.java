package com.epam.jdi.uitests.web.selenium.elements.complex.table;

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

import com.epam.commons.ReflectionUtils;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.web.selenium.elements.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sergey_Mishanin on 11/18/16.
 */
public class EntityTable<E, R> extends Table implements List<E>{
    private Class<E> entityClass;
    private Class<R> rowClass;


    public EntityTable(Class<E> entityClass){
        if (entityClass == null){
            throw new IllegalArgumentException("Entity type was not specified");
        }

        this.entityClass = entityClass;
        List<String> headers = Arrays.stream(entityClass.getFields())
                .map(Field::getName).collect(Collectors.toList());
        hasColumnHeaders(headers);
    }

    public EntityTable(Class<E> entityClass, Class<R> rowClass){
        this(entityClass);
        this.rowClass = rowClass;
    }

    private R newRow(){
        if (rowClass == null){
            throw new UnsupportedOperationException("Row class was not specified");
        }
        try {
            return rowClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private R castToRow(MapArray<String, ICell> row)
    {
        R newRow = newRow();
        row.pairs.forEach(pair ->
                setRowField(newRow, newRow.getClass().getFields(), pair.key, pair.value));
        return newRow;
    }

    private void setRowField(R entity, Field[] fields, String fieldName, ICell cell)
    {
        Optional<Field> opt = Arrays.stream(fields)
                .filter(f -> f.getName().equalsIgnoreCase(fieldName)).findFirst();

        if (!opt.isPresent()){
            return;
        }

        Field field = opt.get();

        Class clazz = field.getType();
        if (clazz == null)
            return;

        BaseElement value;
        try {
            clazz = clazz.isInterface() ? MapInterfaceToElement.getClassFromInterface(clazz) : clazz;
            value = (BaseElement) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        value.avatar = ((Cell) cell).avatar;
        try {
            FieldUtils.writeField(field, entity, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<R> getRows(String[] colNames)
    {
        List<R> rows = new ArrayList<>();
        for (int i=1; i<=size(); i++){
            MapArray<String, ICell> row = new MapArray<>();
            for (String colName: colNames){
                row.add(columns.getColumn(colName).get(i));
            }
            rows.add(castToRow(row));
        }
        return rows;
    }

    public R getRow(String value, Column column)
    {
        return castToRow(super.row(value, column));
    }

    public R getRow(int rowNum)
    {
        return castToRow(super.row(rowNum));
    }

    public R getRow(String rowName)
    {
        return castToRow(super.row(rowName));
    }

    private E newEntity(){
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private E rowToEntity(MapArray<String, ICell> row) {
        E entity = newEntity();
        if (row == null){
            return entity;
        }

        Field[] fields = entity.getClass().getFields();

        row.pairs
            .forEach(entry -> setEntityField(entity, fields, entry.key, entry.value.getText()));

        return entity;
    }

    public List<E> entities(String[] colNames){
        List<E> entities = new ArrayList<>();
        for (int i=1; i<=size(); i++){
            MapArray<String, ICell> row = new MapArray<>();
            for (String colName: colNames){
                row.add(columns.getColumn(colName).get(i));
            }
            entities.add(rowToEntity(row));
        }
        return entities;
    }

    public List<E> entities(){
        return all();
    }

    public E entity(int rowNum){
        return rowToEntity(rows.getRow(rowNum));
    }

    public E entity(String value, Column column){
        return rowToEntity(row(value, column));
    }

    public E entity(String rowName){
        return rowToEntity(rows.getRow(rowName));
    }

    public List<E> all(){
        return rows.get().values().stream()
                .map(this::rowToEntity).collect(Collectors.toList());
    }

    public E first(){
        return entity(1);
    }

    public E last(){
        return entity(size());
    }

    private void setEntityField(E entity, Field[] fields, String fieldName, String value)
    {
        Optional<Field> opt = Arrays.stream(fields)
                .filter(f -> f.getName().equalsIgnoreCase(fieldName)).findFirst();

        if (!opt.isPresent()){
            return;
        }

        Field field = opt.get();

        try {
            FieldUtils.writeField(field, entity, ReflectionUtils.convertStringToType(value, field), true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return rows.count();
    }

    @Override
    public boolean contains(Object o) {
        return allCells.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return all().iterator();
    }

    @Override
    public Object[] toArray() {
        return all().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return all().toArray(a);
    }

    @Override
    public boolean add(E t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return all().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        return entity(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return all().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return all().lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return all().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return all().listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return all().subList(fromIndex, toIndex);
    }
}
