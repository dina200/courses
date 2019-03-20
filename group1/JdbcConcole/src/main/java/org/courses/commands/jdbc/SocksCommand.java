package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Socks;

import java.util.Scanner;

public class SocksCommand extends CrudCommand<Socks, Integer> {
    private Scanner scanner;

    public SocksCommand(DAO<Socks, Integer> dao, Scanner scanner) {
        super(dao, Socks.class);
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Socks material) {
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
    protected void print(Socks entity) {
//        System.out.println(String.format("Socks { id: %d, name: %s }", entity.getId(), entity.getName()));
    }
}
