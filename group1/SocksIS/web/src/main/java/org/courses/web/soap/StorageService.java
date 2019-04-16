package org.courses.web.soap;

import org.courses.domain.hbm.Storage;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface StorageService {
    boolean checkDB(String connectionString);

    void save(Collection<Storage> entity);

    Storage read(Integer id);

    Collection<Storage> readAll();

    Collection<Storage> find(String filter);

    void delete(Integer id);
}
