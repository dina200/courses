package org.courses.cli.commands.jdbc;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Socks;
import org.courses.domain.hbm.Statistic;
import org.courses.domain.hbm.Storage;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Scanner;

public class StorageCommand extends CrudCommand<Storage, Integer> {
    private Scanner scanner;
    private DAO<Socks, Integer> socksDao;
    private DAO<Statistic, Integer> statisticDao;

    public StorageCommand(DAO<Storage, Integer> dao,
                          DAO<Socks, Integer> socksDao,
                          DAO<Statistic, Integer> statisticDao,
                          Scanner scanner) {
        super(dao, Storage.class);
        this.socksDao = socksDao;
        this.statisticDao = statisticDao;
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Storage entity) {
        if (entity.getSocks() == null)
            setSocks(entity);
        if (entity.getAdded() == null)
            setTimeAdding(entity);
        setTimeRetired(entity);
        setUsage(entity);
    }


    private void setSocks(Storage entity) {
        System.out.print("\tsocks: ");
        if (scanner.hasNext()) {
            int socks = scanner.nextInt();
            entity.setSocks(socksDao.read(socks));
        }
    }

    private void setTimeAdding(Storage entity) {
        entity.setAdded(new DateTime());
    }

    private void setTimeRetired(Storage entity) {
        System.out.print("\tset time retired? (y/n) ");
        if (scanner.hasNext()) {
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                entity.setAdded(new DateTime());
            } else {
                throw new RuntimeException("retired time is not added");
            }
        }
    }

    private void setUsage(Storage entity) {

    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Storage entity) {
        System.out.println(entity);
    }
}
