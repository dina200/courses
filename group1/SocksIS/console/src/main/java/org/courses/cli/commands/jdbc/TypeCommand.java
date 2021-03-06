package org.courses.cli.commands.jdbc;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.Type;

import java.util.Scanner;

public class TypeCommand extends CrudCommand<Type, Integer> {
    private Scanner scanner;

    public TypeCommand(DAO<Type, Integer> dao, Scanner scanner) {
        super(dao, Type.class);
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Type type) {
        System.out.print("\tname: ");
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            type.setName(name);
        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Type entity) {
        System.out.println(entity);
    }
}
