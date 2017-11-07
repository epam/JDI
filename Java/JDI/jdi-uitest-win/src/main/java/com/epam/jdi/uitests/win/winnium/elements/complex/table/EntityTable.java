package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.linqinterfaces.JFuncTREx;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.IEntityTable;
import com.epam.jdi.uitests.win.winnium.elements.BaseElement;
import com.epam.jdi.uitests.win.winnium.elements.base.SelectElement;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.ReflectionUtils.convertStringToType;
import static com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement.getClassFromInterface;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;

public class EntityTable<E, R> extends Table implements IEntityTable<E, R> {
    private Class<E> entityClass;
    private Class<R> rowClass;

    public EntityTable(Class<E> entityClass){
        if (entityClass == null)
            throw new IllegalArgumentException("Entity type was not specified");

        this.entityClass = entityClass;
    }

    public EntityTable(Class<E> entityClass, Class<R> rowClass){
        this(entityClass);
        this.rowClass = rowClass;
    }
    public List<R> getRows() {
        return select(rows().get(), row -> castToRow(row.value));
    }
    public List<R> getRows(JFuncTREx<R, Boolean> colNames) {
        List<R> rows = where(getRows(), colNames);
        if (rows.size() == 0)
            logger.info("Can't find any rows that meat criterias");
        return rows;
    }
    public R firstRow(JFuncTREx<R, Boolean> colNames) {
        List<R> rows = getRows(colNames);
        return rows.size() > 0
                ? rows.get(0)
                : null;
    }

    private R castToRow(MapArray<String, ICell> row) {
        R newRow = newRow();
        row.pairs.forEach(pair ->
                setRowField(newRow, newRow.getClass().getFields(), pair.key, pair.value));
        return newRow;
    }

    private R newRow(){
        if (rowClass == null)
            throw exception("Row class was not specified");
        try {
            return rowClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception("Can't Instantiate row: " + rowClass.getName());
        }
    }

    private void setRowField(R row, Field[] fields, String fieldName, ICell cell)
    {
        setField(row, Arrays.asList(fields), fieldName, field -> {
            Class clazz = field.getType();
            if (clazz == null) return null;
            BaseElement value;
            try {
                if (clazz.isInterface())
                    clazz = getClassFromInterface(clazz);
                value = (BaseElement) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw exception("Can't Instantiate row element: " + fieldName);
            }

            value.setAvatar(cell.get(SelectElement.class).getAvatar());
            return value;
        });
    }

    @Override
    public R getRow(String value, Column column) {
        return castToRow(super.row(value, column));
    }

    @Override
    public R getRow(int rowNum) {
        return castToRow(super.row(rowNum));
    }

    @Override
    public R getRow(String rowName) {
        return castToRow(super.row(rowName));
    }

    @Override
    public List<E> entities(String... colNames) {
        return Arrays.asList(colNames).stream().map(colName -> rowToEntity(new MapArray<>(size(),
                i -> columns.getColumn(colName).get(i)))).collect(Collectors.toList());
    }

    @Override
    public E entity(int rowNum) {
        return rowToEntity(rows.getRow(rowNum));
    }

    @Override
    public E entity(String value, Column column) {
        return rowToEntity(row(value, column));
    }

    @Override
    public E entity(String rowName) {
        return rowToEntity(rows.getRow(rowName));
    }

    private E newEntity(){
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception("Can't instantiate %s. You must have empty constructor to do this",
                    entityClass.getSimpleName());
        }
    }

    private void setEntityField(E entity, List<Field> fields, String fieldName, String value) {
        setField(entity, fields, fieldName, field -> convertStringToType(value, field));
    }

    private void setField(Object row, List<Field> fields, String fieldName, Function<Field, Object> valueFunc) {
        Optional<Field> fieldOPt = fields.stream().filter(f -> f.getName().equalsIgnoreCase(fieldName)).
                findAny();

        if (!fieldOPt.isPresent())
            return;

        Field field = fieldOPt.get();
        try {
            writeField(field, row, valueFunc.apply(field), true);
        } catch (IllegalAccessException e) {
            throw exception("Can't write field with name: " + fieldName);
        }
    }

    private E rowToEntity(MapArray<String, ICell> row) {
        E entity = newEntity();
        if (row == null)
            return entity;

        List<Field> fieldsList = Arrays.asList(entity.getClass().getFields());
        row.pairs.forEach(entry
                -> setEntityField(entity, fieldsList, entry.key, entry.value.getText()));
        return entity;
    }

    @Override
    public List<E> all() {
        return rows.get().values().stream().map(this::rowToEntity).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return rows.count();
    }

    @Override
    public boolean contains(Object o) {
        return getCells().contains(o);
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
    public <T> T[] toArray(T[] a) {
        return all().toArray(a);
    }

    @Override
    public boolean add(E e) {
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
