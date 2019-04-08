package org.courses.web.soap;

import javax.jws.WebService;

@WebService
public interface TestService {
    boolean checkDB(String connectionString);
}
