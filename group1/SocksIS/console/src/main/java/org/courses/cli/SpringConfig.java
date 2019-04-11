package org.courses.cli;

import org.courses.cli.commands.Command;
import org.courses.cli.commands.jdbc.*;
import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
@Import(org.courses.data.SpringConfig.class)
public class SpringConfig {
    @Autowired
    DAO<Type, Integer> typeDao;

    @Autowired
    DAO<Socks, Integer> socksDao;

    @Autowired
    DAO<Material, Integer> materialDao;

    @Autowired
    DAO<Manufacture, Integer> manufactureDao;

    @Autowired
    DAO<Storage, Integer> storageDao;

    @Autowired
    DAO<Statistic, Integer> statisticDao;

    @Autowired
    DAO<Composition, Integer> compositionDao;

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public CrudCommand<Type, Integer> typeCommand() {
        return new TypeCommand(typeDao, scanner());
    }

    @Bean
    public CrudCommand<Manufacture, Integer> manufactureCommand() {
        return new ManufactureCommand(manufactureDao, scanner());
    }

    @Bean
    public CrudCommand<Socks, Integer> socksCommand() {
        return new SocksCommand(socksDao, typeDao, materialDao, manufactureDao, compositionDao, scanner());
    }

    @Bean
    public CrudCommand<Material, Integer> materialCommand() {
        return new MaterialCommand(materialDao, scanner());
    }

    @Bean
    public CrudCommand<Storage, Integer> storageCommand() {
        return new StorageCommand(storageDao, socksDao, scanner());
    }

    @Bean
    public CrudCommand<Statistic, Integer> statisticCommand() {
        return new StatisticCommand(statisticDao, storageDao, scanner());
    }

    @Bean
    public CrudCommand<Composition, Integer> compositionCommand() {
        return new CrudCommand<Composition, Integer>(compositionDao, Composition.class) {
            @Override
            protected void readEntity(Composition composition) {
            }

            @Override
            protected Integer convertId(String id) {
                return null;
            }

            @Override
            protected void print(Composition composition) {
            }
        };
    }

    @Bean(name = "commands")
    public Map<String, Command> commands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put("type", typeCommand());
        commands.put("socks", socksCommand());
        commands.put("material", materialCommand());
        commands.put("manufacture", manufactureCommand());
        commands.put("storage", storageCommand());
        commands.put("statistic", statisticCommand());

        return commands;
    }
}
