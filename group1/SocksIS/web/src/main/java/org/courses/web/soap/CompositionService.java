package org.courses.web.soap;

import org.courses.domain.hbm.Composition;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface CompositionService {
    boolean checkDB(String connectionString);

    void save(Collection<Composition> entity);

    Composition read(Integer id);

    Collection<Composition> readAll();

    Collection<Composition> find(String filter);

    void delete(Integer id);
}
