package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Storage;

import java.util.Scanner;

public class StorageCommand extends CrudCommand<Storage, Integer> {
    private Scanner scanner;

    public StorageCommand(DAO<Storage, Integer> dao, Scanner scanner) {
        super(dao, Storage.class);
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Storage material) {
//        System.out.print("name: ");
//        if (scanner.hasNext()) {
//            String name = scanner.nextLine();
//            material.setName(name);
//        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Storage entity) {
//        System.out.println(String.format("Storage { id: %d, name: %s }", entity.getId(), entity.getName()));
    }
}
