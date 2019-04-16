package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Manufacture;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.ManufactureService",
        serviceName = "ManufactureService"
)
public class DbManufactureService implements ManufactureService {
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Manufacture, Integer> dao;

    public DbManufactureService(DAO<Manufacture, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void save(Collection<Manufacture> entity) {
        dao.save(entity);
    }
    @Override
    public Manufacture read(Integer id) {
        return dao.read(id);
    }
    @Override
    public Collection<Manufacture> readAll() {
        return dao.readAll();
    }
    @Override
    public Collection<Manufacture> find(String filter) {
        return dao.find(filter);
    }
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
