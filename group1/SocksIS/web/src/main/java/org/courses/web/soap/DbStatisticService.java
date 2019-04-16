package org.courses.web.soap;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Statistic;

import javax.jws.WebService;
import java.util.Collection;

@WebService(
        endpointInterface = "org.courses.web.soap.StatisticService",
        serviceName = "StatisticService"
)
public class DbStatisticService implements StatisticService {
    public boolean checkDB(String connectionString) {
        return false;
    }

    DAO<Statistic, Integer> dao;

    public DbStatisticService(DAO<Statistic, Integer> dao) {
        this.dao = dao;
    }
    @Override
    public void save(Collection<Statistic> entity) {
        dao.save(entity);
    }
    @Override
    public Statistic read(Integer id) {
        return dao.read(id);
    }
    @Override
    public Collection<Statistic> readAll() {
        return dao.readAll();
    }
    @Override
    public Collection<Statistic> find(String filter) {
        return dao.find(filter);
    }
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
