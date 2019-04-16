package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Type;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.TypeService",
        serviceName = "TypeService"
)
public class DbTypeService implements TypeService {

    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Type, Integer> dao;

    public DbTypeService(DAO<Type, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void save(Collection<Type> entity) {
        dao.save(entity);
    }

    @Override
    public Type read(Integer id) {
        return dao.read(id);
    }

    @Override
    public Collection<Type> readAll() {
        return dao.readAll();
    }

    @Override
    public Collection<Type> find(String filter) {
        return dao.find(filter);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
