package org.courses.web.soap;

import org.courses.domain.hbm.Type;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface TypeService {
    boolean checkDB(String connectionString);

    void save(Collection<Type> entity);

    Type read(Integer id);

    Collection<Type> readAll();

    Collection<Type> find(String filter);

    void delete(Integer id);
}
