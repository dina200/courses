package org.courses.cli.commands.jdbc;

import org.courses.data.DAO.DAO;
import org.courses.domain.hbm.*;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by dleb on 03/21/2019.
 */
public class SocksCommand extends CrudCommand<Socks, Integer> {
    private Scanner scanner;
    DAO<Type, Integer> typeDao;
    DAO<Material, Integer> materialDao;
    DAO<Manufacture, Integer> manufactureDao;

    public SocksCommand(DAO<Socks, Integer> dao,
                        DAO<Type, Integer> typeDao,
                        DAO<Material, Integer> materialDao,
                        DAO<Manufacture, Integer> manufactureDao,
                        Scanner scanner) {
        super(dao, Socks.class);
        this.scanner = scanner;
        this.typeDao = typeDao;
        this.materialDao = materialDao;
        this.manufactureDao = manufactureDao;
    }

    @Override
    protected void readEntity(Socks socks) {
        System.out.print("\tcolour: ");
        if (scanner.hasNext()) {
            int color = scanner.nextInt();
            socks.setColour(new Color(color));
        }
        System.out.print("\tsize: ");
        if (scanner.hasNext()) {
            double size = scanner.nextDouble();
            socks.setSize(size);
        }
        System.out.print("\ttype: ");
        if (scanner.hasNext()) {
            int type = scanner.nextInt();
            socks.setType(typeDao.read(type));
        }
        System.out.print("\tmanufacture: ");
        if (scanner.hasNext()) {
            int manufacture = scanner.nextInt();
            socks.setManufacture(manufactureDao.read(manufacture));
        }

        System.out.println("\tcomposition: ");
        int percentage = 0;
        while (percentage < 100) {
            Composition c = new Composition();

            System.out.print("\tmaterial: ");
            if (scanner.hasNext()) {
                int id = scanner.nextInt();
                c.setMaterial(materialDao.read(id));
            }
            System.out.print("\tpercantage: ");
            if (scanner.hasNext()) {
                int percents = scanner.nextInt();
                c.setPercentage(percents);
                percentage += percents;
            }

            socks.add(c);
        }
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Socks socks) {
        System.out.println(String.format(
                "\tSocks { id: %d, composition: [ ", socks.getId()
        ));
        for (Composition c : socks.getComposition()) {
            System.out.println(String.format(
                "\t\tComposition { id: %d, percentage: %d, material: %d },",
                c.getId(), c.getPercentage(), c.getMaterial().getId()
            ));
        }
        System.out.println("\t]}");
    }
}
