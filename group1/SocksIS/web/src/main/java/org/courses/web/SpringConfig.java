package org.courses.web;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.*;
import org.courses.web.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.xml.ws.Endpoint;

@Configuration
@Import(org.courses.data.SpringConfig.class)
@ImportResource("classpath:META-INF/cxf/cxf.xml")
public class SpringConfig {
    @Autowired
    DAO<Type, Integer> typeDao;

    @Autowired
    DAO<Material, Integer> materialDao;

    @Autowired
    DAO<Socks, Integer> socksDao;

    @Autowired
    DAO<Manufacture, Integer> manufactureDao;

    @Autowired
    DAO<Composition, Integer> compositionDao;

    @Autowired
    DAO<Storage, Integer> storageDao;

    @Autowired
    DAO<Statistic, Integer> statisticDao;

    @Bean
    public TypeService soapTypeService() {
        return new DbTypeService(typeDao);
    }

    @Bean
    public MaterialService soapMaterialService() {
        return new DbMaterialService(materialDao);
    }

    @Bean
    public ManufactureService soapManufactureService() {
        return new DbManufactureService(manufactureDao);

    }
    @Bean
    public SocksService soapSocksService() {
        return new DbSocksService(socksDao);
    }

    @Bean
    public CompositionService soapCompositionService() {
        return new DbCompositionService(compositionDao);
    }

    @Bean
    public StorageService soapStorageService() {
        return new DbStorageService(storageDao);
    }

    @Bean
    public StatisticService soapStatisticService() {
        return new DbStatisticService(statisticDao);
    }

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpointType() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapTypeService());
        endpoint.publish("/type");
        return endpoint;
    }

    @Bean
    public Endpoint endpointMaterial() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapMaterialService());
        endpoint.publish("/material");
        return endpoint;
    }

    @Bean
    public Endpoint endpointManufacture() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapManufactureService());
        endpoint.publish("/manufacture");
        return endpoint;
    }

    @Bean
    public Endpoint endpointSocks() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapSocksService());
        endpoint.publish("/socks");
        return endpoint;
    }

    @Bean
    public Endpoint endpointComposition() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapCompositionService());
        endpoint.publish("/composition");
        return endpoint;
    }

    @Bean
    public Endpoint endpointStorage() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapStorageService());
        endpoint.publish("/storage");
        return endpoint;
    }

    @Bean
    public Endpoint endpointStatistic() {
        EndpointImpl endpoint = new EndpointImpl(cxf(), soapStatisticService());
        endpoint.publish("/statistic");
        return endpoint;
    }


    @Bean
    public org.courses.web.rest.TestService restTestService() {
        return new org.courses.web.rest.TestService();
    }
}
