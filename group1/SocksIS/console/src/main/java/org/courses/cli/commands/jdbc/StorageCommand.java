package org.courses.cli.commands.jdbc;

import org.courses.cli.commands.CommandFormatException;
import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Socks;
import org.courses.domain.hbm.Storage;
import org.joda.time.DateTime;

import java.util.Scanner;

public class StorageCommand extends CrudCommand<Storage, Integer> {
    private Scanner scanner;
    private DAO<Socks, Integer> socksDao;

    public StorageCommand(DAO<Storage, Integer> dao,
                          DAO<Socks, Integer> socksDao,
                          Scanner scanner) {
        super(dao, Storage.class);
        this.socksDao = socksDao;
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Storage entity) {
        if (entity.getSocks() == null)
            setSocks(entity);
        if (entity.getAdded() == null)
            setTimeAdding(entity);
        setTimeRetired(entity);
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
            String answer = scanner.next();
            if (answer.equals("y")) {
                entity.setRetired(new DateTime());
            }
        }
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
