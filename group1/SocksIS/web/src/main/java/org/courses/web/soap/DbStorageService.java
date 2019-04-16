package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Storage;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.StorageService",
        serviceName = "StorageService"
)
public class DbStorageService implements StorageService {
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Storage, Integer> dao;

    public DbStorageService(DAO<Storage, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void save(Collection<Storage> entity) {
        dao.save(entity);
    }
    @Override
    public Storage read(Integer id) {
        return dao.read(id);
    }
    @Override
    public Collection<Storage> readAll() {
        return dao.readAll();
    }
    @Override
    public Collection<Storage> find(String filter) {
        return dao.find(filter);
    }
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
