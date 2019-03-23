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
        addSize(socks);
        addColor(socks);
        addType(socks);
        addManufacture(socks);
        addMaterial(socks);
    }

    private void addSize(Socks socks) {
        System.out.print("\tsize: ");
        if (scanner.hasNext()) {
            double size = scanner.nextDouble();
            socks.setSize(size);
        }
    }

    private void addColor(Socks socks) {
        System.out.print("\tcolour: ");
        if (scanner.hasNext()) {
            int color = scanner.nextInt();
            socks.setColour(new Color(color));
        }
    }

    private void addType(Socks socks) {
        System.out.print("\ttype: ");
        if (scanner.hasNext()) {
            int type = scanner.nextInt();
            socks.setType(typeDao.read(type));
        }
    }

    private void addManufacture(Socks socks) {
        System.out.print("\tmanufacture: ");
        if (scanner.hasNext()) {
            int manufacture = scanner.nextInt();
            socks.setManufacture(manufactureDao.read(manufacture));
        }
    }

    private void addMaterial(Socks socks) {
        System.out.println("\t---composition--- ");
        int generalPercentage = 0;
        int percentage;
        Material material;
        while (generalPercentage < 100) {

            System.out.print("\tmaterial: ");
            if (scanner.hasNext()) {
                int material_id = scanner.nextInt();
                material = materialDao.read(material_id);
            } else {
                continue;
            }
            System.out.print("\tpercantage: ");
            if (scanner.hasNext()) {
                percentage = scanner.nextInt();
                if (percentage >= 100) {
                    percentage = 100 - generalPercentage;
                }
                generalPercentage += percentage;
            } else {
                continue;
            }
            Composition composition = compositionInstance(percentage, material);
            socks.add(composition);
        }
    }

    private Composition compositionInstance(int percentage, Material material) {
        Composition composition = new Composition();
        composition.setPercentage(percentage);
        composition.setMaterial(material);
        return composition;
    }

    @Override
    protected Integer convertId(String id) {
        return Integer.parseInt(id);
    }

    @Override
    protected void print(Socks socks) {
        System.out.println(socks);
    }

//    @Override
//    protected void print(Socks socks) {
//        System.out.println(String.format(
//                "\tSocks { id: %d, composition: [ ", socks.getId()
//        ));
//        for (Composition c : socks.getCompositions()) {
//            System.out.println(String.format(
//                    "\t\tComposition { id: %d, percentage: %d, material: %d },",
//                    c.getId(), c.getPercentage(), c.getMaterial().getId()
//            ));
//        }
//        System.out.println("\t]}");
//    }
}
