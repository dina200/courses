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

    @Bean
    public TypeService soapTypeService() {
        return new DbTypeService(typeDao);
    }

    @Bean
    public MaterialService soapMaterialService() {
        return new DbMaterialService(materialDao);
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
    public org.courses.web.rest.TestService restTestService() {
        return new org.courses.web.rest.TestService();
    }
//    @Autowired
//    DAO<Socks, Integer> socksDao;

//    @Autowired
//    DAO<Manufacture, Integer> manufactureDao;
//
//    @Autowired
//    DAO<Composition, Integer> compositionDao;
//
//    @Autowired
//    DAO<Storage, Integer> storageDao;
//
//    @Autowired

//    DAO<Statistic, Integer> statisticDao;

//    @Bean
//    public org.courses.web.soap.TypeService<Manufacture, Integer> soapManufactureService() {
//        return new DbTypeService(manufactureDao);
//    }
//
//    @Bean
//    public org.courses.web.soap.TypeService<Socks, Integer> soapSocksService() {
//        return new DbTypeService(socksDao);
//    }
//
//    @Bean
//    public org.courses.web.soap.TypeService<Composition, Integer> soapCompositionService() {
//        return new DbTypeService(compositionDao);
//    }
//
//    @Bean
//    public org.courses.web.soap.TypeService<Storage, Integer> soapStorageService() {
//        return new DbTypeService(storageDao);
//    }
//
//    @Bean
//    public org.courses.web.soap.TypeService<Statistic, Integer> soapStatisticService() {
//        return new DbTypeService(statisticDao);
//    }


//
//    @Bean(name = "manufacture")
//    public Endpoint endpointManufacture() {
//        EndpointImpl endpoint = new EndpointImpl(cxf(), soapManufactureService());
//        endpoint.publish("/manufacture");
//        return endpoint;
//    }
//
//    @Bean(name = "socks")
//    public Endpoint endpointSocks() {
//        EndpointImpl endpoint = new EndpointImpl(cxf(), soapSocksService());
//        endpoint.publish("/socks");
//        return endpoint;
//    }
//
//    @Bean(name = "composition")
//    public Endpoint endpointComposition() {
//        EndpointImpl endpoint = new EndpointImpl(cxf(), soapCompositionService());
//        endpoint.publish("/composition");
//        return endpoint;
//    }
//
//    @Bean(name = "storage")
//    public Endpoint endpointStorage() {
//        EndpointImpl endpoint = new EndpointImpl(cxf(), soapStorageService());
//        endpoint.publish("/storage");
//        return endpoint;
//    }
//
//    @Bean(name = "statistic")
//    public Endpoint endpointStatistic() {
//        EndpointImpl endpoint = new EndpointImpl(cxf(), soapStatisticService());
//        endpoint.publish("/statistic");
//        return endpoint;
//    }


}
