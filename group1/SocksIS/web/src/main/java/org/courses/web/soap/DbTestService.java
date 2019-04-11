package org.courses.web.soap;

import org.courses.data.DAO.DAO;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.TestService",
        serviceName = "TestService"
)
public class DbTestService<Entity, Key> implements TestService<Entity, Key> {
    @Override
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Entity, Key> dao;

    public DbTestService(DAO<Entity, Key> dao) {
        this.dao = dao;
    }

    @Override
    public void save(Collection<Entity> entity) {
        dao.save(entity);
    }

    @Override
    public Entity read(Key id) {
        return dao.read(id);
    }

    @Override
    public Collection<Entity> readAll() {
        return dao.readAll();
    }

    @Override
    public Collection<Entity> find(String filter) {
        return dao.find(filter);
    }

    @Override
    public void delete(Key id) {
        dao.delete(id);
    }
}
