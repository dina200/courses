package org.courses.web.soap;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface TestService<Entity, Key> {
    boolean checkDB(String connectionString);
    void save(Collection<Entity> entity);
    Entity read(Key id);
    Collection<Entity> readAll();
    Collection<Entity> find(String filter);
    void delete(Key id);
}
