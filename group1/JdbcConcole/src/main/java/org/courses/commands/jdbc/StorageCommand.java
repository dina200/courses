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
    protected void readEntity(Storage  entity) {
//        System.out.print("name: ");
//        if (scanner.hasNext()) {
//            String name = scanner.nextLine();
//            entity.setName(name);
//        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Storage entity) {
        System.out.println(String.format("Storage { id: %d, socksId: %d, added: %s, retired: %s, usage: %s }",
                entity.getId(),
                entity.getSocks().getId(),
                entity.getAdded().toString("yyyy-MM-dd"),
                entity.getRetired().toString("yyyy-MM-dd"),
                entity.getUsage().toString())
        );
    }
}
