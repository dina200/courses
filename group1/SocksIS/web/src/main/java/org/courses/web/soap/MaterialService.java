package org.courses.web.soap;

import org.courses.domain.hbm.Material;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface MaterialService{
    boolean checkDB(String connectionString);

    void save(Collection<Material> entity);

    Material read(Integer id);

    Collection<Material> readAll();

    Collection<Material> find(String filter);

    void delete(Integer id);
}
