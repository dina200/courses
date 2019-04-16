package org.courses.web.soap;

import org.courses.domain.hbm.Manufacture;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ManufactureService {
    boolean checkDB(String connectionString);

    void save(Collection<Manufacture> entity);

    Manufacture read(Integer id);

    Collection<Manufacture> readAll();

    Collection<Manufacture> find(String filter);

    void delete(Integer id);
}
