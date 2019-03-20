package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.*;
import org.courses.domain.hbm.sqliteconvertion.ColorConverter;

import java.util.Scanner;

public class SocksCommand extends CrudCommand<Socks, Integer> {
    private Scanner scanner;
    private DAO<Type, Integer> daoType;
    private DAO<Manufacture, Integer> daoManufacture;
    private DAO<Material, Integer> daoMaterial;

    public SocksCommand(
            DAO<Socks, Integer> dao,
            Scanner scanner,
            DAO<Type, Integer> daoType,
            DAO<Manufacture, Integer> daoManufacture,
            DAO<Material, Integer> daoMaterial) {
        super(dao, Socks.class);
        this.scanner = scanner;
        this.daoType = daoType;
        this.daoManufacture = daoManufacture;
        this.daoMaterial = daoMaterial;
    }

    @Override
    protected void readEntity(Socks entity) {
        System.out.println("Add all (a) or some attributes (s)? ['a' / 's']");
        String answer = scanner.nextLine();
        if (answer.equals("a")) {
            addNewSocks(entity);
            return;
        } else if (answer.equals("s")) {
            updateSocks(entity);
            return;
        } else {
            System.out.println("Incorrect command, try again: ['a' / 's']");
        }
    }

    private void addNewSocks(Socks entity) {
        addSize(entity);
        addColor(entity);
        addType(entity);
        addManufacture(entity);
        addMaterial(entity);
    }

    private void updateSocks(Socks entity) {
        System.out.println("1 - size\n 2 - color\n3- type\n4 - manufacture\n5 - socks material\nany number - cancel");
        int ans = 0;
        if (scanner.hasNext()) {
            try {
                ans = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new CommandFormatException("NumberFormatException: incorrect data");
            }
            switch (ans) {
                case 1:
                    addSize(entity);
                    break;
                case 2:
                    addColor(entity);
                    break;
                case 3:
                    addType(entity);
                    break;
                case 4:
                    addManufacture(entity);
                    break;
                case 5:
                    addMaterial(entity);
                    break;
            }
        }
    }

    private void addSize(Socks entity) {
        System.out.print("size: ");
        if (scanner.hasNext()) {
            try {
                double size = Double.parseDouble(scanner.nextLine());
                entity.setSize(size);
            } catch (NumberFormatException e) {
                throw new CommandFormatException("NumberFormatException: incorrect data");
            }
        }
    }

    private void addColor(Socks entity) {
        System.out.print("color (int): ");
        if (scanner.hasNext()) {
            try {
                int color = Integer.parseInt(scanner.nextLine());
                entity.setColour(new ColorConverter().convertToEntityAttribute(color));
            } catch (NumberFormatException e) {
                throw new CommandFormatException("NumberFormatException: incorrect data");
            }
        }
    }

    private void addType(Socks entity) {
        System.out.println("type_id: ");
        if (scanner.hasNext()) {
            try {
                int type_id = Integer.parseInt(scanner.nextLine());
                Type type = daoType.read(type_id);
                entity.setType(type);
            } catch (NumberFormatException e) {
                throw new CommandFormatException("NumberFormatException: incorrect data");
            }
        }
    }

    private void addManufacture(Socks entity) {
        System.out.println("manufacture_id: ");
        if (scanner.hasNext()) {
            try {
                int manufacture_id = Integer.parseInt(scanner.nextLine());
                Manufacture manufacture = daoManufacture.read(manufacture_id);
                entity.setManufacture(manufacture);
            } catch (NumberFormatException e) {
                throw new CommandFormatException("NumberFormatException: incorrect data");
            }
        }
    }

    private void addMaterial(Socks entity) {
        //???
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Socks entity) {
        System.out.println(entity);
    }
}
