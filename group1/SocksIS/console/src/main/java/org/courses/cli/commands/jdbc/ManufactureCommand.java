package org.courses.cli.commands.jdbc;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Manufacture;

import java.util.Scanner;

public class ManufactureCommand extends CrudCommand<Manufacture, Integer> {
    private Scanner scanner;

    public ManufactureCommand(DAO<Manufacture, Integer> dao, Scanner scanner) {
        super(dao, Manufacture.class);
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Manufacture entity) {
        System.out.print("name: ");
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            entity.setName(name);
        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Manufacture entity) {
        System.out.println(entity);
    }
}

