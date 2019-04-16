package org.courses.web.soap;

import org.courses.domain.hbm.Statistic;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface StatisticService {
    boolean checkDB(String connectionString);

    void save(Collection<Statistic> entity);

    Statistic read(Integer id);

    Collection<Statistic> readAll();

    Collection<Statistic> find(String filter);

    void delete(Integer id);
}
