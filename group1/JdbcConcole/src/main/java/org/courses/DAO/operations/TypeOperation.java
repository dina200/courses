package org.courses.DAO.operations;

import org.courses.DAO.DAOInterface;
import org.courses.DAO.StaticDAOOperation;
import org.courses.DAO.entities.Type;
import org.courses.domain.jdbc.BaseEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOperation implements DAOInterface {
    private Connection connection;

    public TypeOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Save(BaseEntity o) throws SQLException, IllegalAccessException {
        Type type = (Type) o;
        if (type.getId() == -1) {
            StaticDAOOperation.insert(type, this.connection);
        } else {
            //some update operation
        }
    }

    @Override
    public BaseEntity Read(int id) throws SQLException {
        String filter = String.format("id = %s", id);
        ResultSet results = StaticDAOOperation.select("Type", "*", filter, this.connection);
        results.next();
        String name = results.getString("name");
        results.close();
        return new Type(id, name);
    }

    @Override
    public List<BaseEntity> ReadAll() throws SQLException {
        String filter = "1 = 1";
        ResultSet results = StaticDAOOperation.select("Type", "*", filter, this.connection);
        List<BaseEntity> daoSockTypeList = new ArrayList<>();
        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            daoSockTypeList.add(new Type(id, name));
        }
        results.close();
        return daoSockTypeList;
    }

    @Override
    public void Delete() {

    }
}
