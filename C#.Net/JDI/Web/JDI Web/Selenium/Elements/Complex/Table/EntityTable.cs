using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using JDI_Commons;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using JDI_Web.Settings;
using static JDI_Web.Selenium.Attributes.GetElementClass;

namespace JDI_Web.Selenium.Elements.Complex.Table
{
    public class Table<TEntity, TRow> : Table<TEntity>
    {
        private TRow NewRow => Activator.CreateInstance<TRow>();
        private TRow CastToRow(Dictionary<string, ICell> row)
        {
            var newRow = NewRow;
            row.ForEach(pair =>
                SetRowField(newRow, newRow.GetFields(), pair.Key, pair.Value));
            return newRow;
        }
        private static void SetRowField(TRow entity, List<FieldInfo> fields, string fieldName, ICell cell)
        {
            var field = fields.FirstOrDefault(f => NamesEqual(f.Name, fieldName));
            var clazz = field?.FieldType;
            if (clazz == null) return;
            var value = (WebBaseElement) Activator.CreateInstance(clazz.IsInterface
                ? MapInterfaceToElement.ClassFromInterface(clazz)
                : clazz);
            value.WebAvatar = ((Cell)cell).WebAvatar;
            field.SetValue(entity, value);
        }

        public new IList<TRow> Rows(params string[] colNameValues)
        {
            return GetRows(colNameValues).Select(r => CastToRow(r.Value)).ToList();
        }

        public new TRow Row(string value, Column column)
        {
            return CastToRow(base.Row(value, column));
        }

        public new TRow Row(int rowNum)
        {
            return CastToRow(base.Row(rowNum));
        }

        public new TRow Row(string rowName)
        {
            return CastToRow(base.Row(rowName));
        }
    }

    public class Table<TEntity> : Table
    {
        public Table()
        {
            ColumnHeaders = typeof(TEntity).GetFieldsList().Select(f => f.Name).ToArray();
        }

        private TEntity NewEntity => Activator.CreateInstance<TEntity>();
        
        private TEntity RowToEntity(Dictionary<string, ICell> row)
        {
            var entity = NewEntity;
            var fields = entity.GetFields();
            row.ForEach(pair => 
                SetEntityField(entity, fields, pair.Key, pair.Value.GetText));
            return entity;
        }

        private static void SetEntityField(TEntity entity, List<FieldInfo> fields, string fieldName, string value)
        {
            var field = fields.FirstOrDefault(f => NamesEqual(f.Name, fieldName));
            field?.SetValue(entity, value.ConvertStringToType(field));
        }

        public IList<TEntity> Entities(params string[] colNameValues)
        {
            return GetRows(colNameValues).Select(r => RowToEntity(r.Value)).ToList();
        }
        
        public TEntity Entity(string value, Column column)
        {
            return RowToEntity(Row(value, column));
        }

        public TEntity Entity(int rowNum)
        {
            return RowToEntity(Row(rowNum));
        }

        public TEntity Entity(string rowName)
        {
            return RowToEntity(Row(rowName));
        }
    }
}
