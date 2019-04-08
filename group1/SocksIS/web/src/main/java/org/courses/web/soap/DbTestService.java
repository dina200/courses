package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Material;

import javax.jws.WebService;

@WebService(
        endpointInterface = "org.courses.web.soap.TestService",
        serviceName = "TestService"
)
public class DbTestService implements TestService {
    DAO<Material, Integer> dao;

    public DbTestService(DAO<Material, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public boolean checkDB(String connectionString) {
        return false;
    }
}
