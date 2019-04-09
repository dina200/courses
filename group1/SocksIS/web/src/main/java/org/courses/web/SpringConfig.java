package org.courses.web;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Material;
import org.courses.web.soap.DbTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.xml.ws.Endpoint;

@Configuration
@Import(org.courses.data.SpringConfig.class)
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class SpringConfig {
    @Autowired
    DAO<Material, Integer> materialDao;

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public org.courses.web.soap.TestService soapTestService() {
        return new DbTestService(materialDao);
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapTestService());
        endpoint.publish("/testservice");
        return endpoint;
    }

    @Bean
    public org.courses.web.rest.TestService restTestService() {
        return new org.courses.web.rest.TestService();
    }
}
