package org.courses.DAO;

import org.courses.domain.jdbc.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface {
   void Save(BaseEntity o) throws SQLException, IllegalAccessException;
   BaseEntity Read(int id) throws SQLException;
   List<BaseEntity> ReadAll() throws SQLException;
   void Delete(int id) throws SQLException;
}
