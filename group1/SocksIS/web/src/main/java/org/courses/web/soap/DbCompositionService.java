package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Composition;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.CompositionService",
        serviceName = "CompositionService"
)
public class DbCompositionService implements CompositionService {
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Composition, Integer> dao;

    public DbCompositionService(DAO<Composition, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void save(Collection<Composition> entity) {
        dao.save(entity);
    }
    @Override
    public Composition read(Integer id) {
        return dao.read(id);
    }
    @Override
    public Collection<Composition> readAll() {
        return dao.readAll();
    }
    @Override
    public Collection<Composition> find(String filter) {
        return dao.find(filter);
    }
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
