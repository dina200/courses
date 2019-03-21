package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class SocksCommand extends CrudCommand<Socks, Integer> {
    private Scanner scanner;
    private DAO<Type, Integer> daoType;
    private DAO<Manufacture, Integer> daoManufacture;
    private DAO<Material, Integer> daoMaterial;
    private DAO<Composition, Integer> daoComposition;

    public SocksCommand(
            DAO<Socks, Integer> dao,
            Scanner scanner,
            DAO<Type, Integer> daoType,
            DAO<Manufacture, Integer> daoManufacture,
            DAO<Material, Integer> daoMaterial,
            DAO<Composition, Integer> daoComposition) {
        super(dao, Socks.class);
        this.scanner = scanner;
        this.daoType = daoType;
        this.daoManufacture = daoManufacture;
        this.daoMaterial = daoMaterial;
        this.daoComposition = daoComposition;
    }

    @Override
    protected void readEntity(Socks entity) {
        addSize(entity);
        addColor(entity);
        addType(entity);
        addManufacture(entity);
        addMaterial(entity);
    }

    private void addSize(Socks entity) {
        System.out.print("size: ");
        if (scanner.hasNext()) {
            double size = scanner.nextDouble();
            entity.setSize(size);
        }
    }

    private void addColor(Socks entity) {
        System.out.print("colour (int): ");
        if (scanner.hasNext()) {
            int color = scanner.nextInt();
            entity.setColour(new Color(color));
        }
    }

    private void addType(Socks entity) {
        System.out.print("type_id: ");
        if (scanner.hasNext()) {
            int type_id = scanner.nextInt();
            Type type = daoType.read(type_id);
            entity.setType(type);
        }
    }

    private void addManufacture(Socks entity) {
        System.out.print("manufacture_id: ");
        if (scanner.hasNext()) {
            int manufacture_id = scanner.nextInt();
            Manufacture manufacture = daoManufacture.read(manufacture_id);
            entity.setManufacture(manufacture);
        }
    }

    private void addMaterial(Socks entity) {
        int generalPercentage = 0;
        int percentage;
        Material material;
        deleteAllCompositions(entity);
        while (generalPercentage < 100) {
            System.out.print("material's percentage: ");
            if (scanner.hasNext()) {
                percentage = scanner.nextInt();
                if (percentage >= 100) {
                    percentage = 100 - generalPercentage;
                }
                generalPercentage += percentage;
            } else {
                continue;
            }
            System.out.print("material_id: ");
            if (scanner.hasNext()) {
                int material_id = scanner.nextInt();
                material = daoMaterial.read(material_id);
            } else {
                continue;
            }
            Composition composition = compositionInstance(percentage, material, entity);
            daoComposition.save(Arrays.asList(composition));
            entity.setComposition(composition);
        }
    }

    private void deleteAllCompositions(Socks entity) {
        for (Composition c : entity.getCompositions()) {
            daoComposition.delete(c.getId());
        }
    }

    private Composition compositionInstance(int percentage, Material material, Socks entity) {
        Composition composition = new Composition();
        composition.setPercentage(percentage);
        composition.setMaterial(material);
        composition.setSocks(entity);
        return composition;
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
