package org.courses.web.soap;

import org.courses.domain.hbm.Socks;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface SocksService {
    boolean checkDB(String connectionString);

    void save(Collection<Socks> entity);

    Socks read(Integer id);

    Collection<Socks> readAll();

    Collection<Socks> find(String filter);

    void delete(Integer id);
}
