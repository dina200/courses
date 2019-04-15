package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Material;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.TestService",
        serviceName = "TestService"
)
public class DbMaterialService implements TestService<Material, Integer> {
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Material, Integer> dao;

    public DbMaterialService(DAO<Material, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void save(Collection<Material> entity) {
        dao.save(entity);
    }
    @Override
    public Material read(Integer id) {
        return dao.read(id);
    }
    @Override
    public Collection<Material> readAll() {
        return dao.readAll();
    }
    @Override
    public Collection<Material> find(String filter) {
        return dao.find(filter);
    }
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
