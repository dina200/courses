package org.courses.DAO;

import org.courses.DAO.entities.Type;
import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticDAOOperation {

    public static void insert(BaseEntity entity, Connection con) throws IllegalAccessException, SQLException {
        String table = entity.getName();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        Collection<Column> definitions = entity.getColumns();
        for (Column definition : definitions) {
            if (columns.length() > 0)
                columns.append(", ");
            columns.append(definition.getName());

            if (values.length() > 0)
                values.append(", ");
            Object value = entity.getColumn(definition);
            if (null == value)
                values.append("NULL");
            else if (value instanceof String)
                values.append(String.format("'%s'", (String) value));
            else
                values.append(value.toString());
        }
        insert(table, columns.toString(), values.toString(), con);
    }

    public static void insert(String table, String columns, String values, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute(String.format("INSERT INTO %s" +
                "(%s) " +
                "VALUES" +
                "(%s)", table, columns, values));
        statement.close();
        //return id of inserted record
    }

    public static List<BaseEntity> select(String table, String columns, String filter, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet results = statement.executeQuery(String.format("SELECT %s " +
                "FROM %s " +
                "WHERE %s ", columns, table, filter));
        List<BaseEntity> list = new ArrayList<>();
        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            list.add(new Type(id, name));
        }
        results.close();
        return list;
    }

    public static void delete(String table, String filter, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute(String.format("DELETE FROM %s " +
                "WHERE %s ", table, filter));
        statement.close();
    }


    public static void update(String table, String record, String filter, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute(String.format("UPDATE %s " +
                "SET %s " +
                "WHERE %s ", table, record, filter));
        statement.close();
    }
}
