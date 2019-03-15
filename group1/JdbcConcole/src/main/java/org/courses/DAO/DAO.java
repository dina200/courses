package org.courses.DAO;

import java.util.Collection;

public interface DAO<Tentity, Tkey> {
    void save(Collection<Tentity> entity);

    Tentity read(Tkey id);

    Collection<Tentity> readAll();

    Collection<Tentity> find(String filter);

    void delete(Tentity entity);

    Tentity getEntity();
}
