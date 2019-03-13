package org.courses.DAO.operations;

import org.courses.DAO.DAOInterface;
import org.courses.DAO.StaticDAOOperation;
import org.courses.DAO.entities.Type;
import org.courses.commands.CommandFormatException;
import org.courses.domain.jdbc.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TypeOperation implements DAOInterface {
    private static final String TYPE = "Type";
    private static final String ID = "id";
    private static final String NAME = "name";
    private Connection connection;

    public TypeOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Save(BaseEntity o) throws SQLException, IllegalAccessException {
        Type type = (Type) o;
        if (type.getId() == -1) {
            StaticDAOOperation.insert(TYPE, NAME, String.format("'%s'", type.getName()), connection);
        } else {
            StaticDAOOperation.update(TYPE, String.format(NAME + " = '%s'", type.getName()), String.format(ID + " = %s", type.getId()), connection);
        }
    }

    @Override
    public BaseEntity Read(int id) throws SQLException {
        String filter = String.format(ID + " = %s", id);
        BaseEntity entity;
        try {
            entity = StaticDAOOperation.select(TYPE, "*", filter, this.connection).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandFormatException("The record does't exist");
        }
        return entity;
    }

    @Override
    public List<BaseEntity> ReadAll() throws SQLException {
        String filter = "1 = 1";
        return StaticDAOOperation.select(TYPE, "*", filter, this.connection);
    }

    @Override
    public void Delete(int id) throws SQLException {
        String filter = String.format(ID + " = %s", id);
        StaticDAOOperation.delete(TYPE, filter, this.connection);
    }
}
