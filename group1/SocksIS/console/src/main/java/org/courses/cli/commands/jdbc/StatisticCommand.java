package org.courses.cli.commands.jdbc;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Statistic;
import org.courses.domain.hbm.Storage;
import org.joda.time.DateTime;

import java.util.Scanner;

public class StatisticCommand extends CrudCommand<Statistic, Integer> {
    private Scanner scanner;
    private DAO<Storage, Integer> storageDao;

    public StatisticCommand(DAO<Statistic, Integer> dao,
                            DAO<Storage, Integer> storageDao,
                            Scanner scanner) {
        super(dao, Statistic.class);
        this.storageDao = storageDao;
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Statistic statistic) {
        System.out.print("\tstorage: ");
        if (scanner.hasNext()) {
            int storage = scanner.nextInt();
            statistic.setStorage(storageDao.read(storage));
        }
        if (statistic.getStartUsing() == null)
            statistic.setStartUsing(new DateTime());
        System.out.print("\tset time stop_using? (y/n) ");
        if (scanner.hasNext()) {
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                statistic.setStopUsing(new DateTime());
            } else {
                throw new RuntimeException("stop_using time is not added");
            }
        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Statistic entity) {
        System.out.println(entity);
    }
}
