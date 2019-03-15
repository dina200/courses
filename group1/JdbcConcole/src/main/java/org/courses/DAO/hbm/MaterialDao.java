package org.courses.DAO.hbm;

import org.courses.domain.hbm.Material;
import org.hibernate.SessionFactory;

public class MaterialDao extends SimpleEntityDao<Material> {
    public MaterialDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Class<Material> getEntityClass() {
        return Material.class;
    }

    @Override
    public String getEntityName() {
        return Material.class.getSimpleName();
    }

    @Override
    public Material getEntity() {
        return new Material();
    }
}
