package org.courses.DAO.hbm;

import org.courses.domain.hbm.Manufacture;
import org.hibernate.SessionFactory;

public class ManufactureDao extends SimpleEntityDao<Manufacture> {
    public ManufactureDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Class<Manufacture> getEntityClass() {
        return Manufacture.class;
    }

    @Override
    public String getEntityName() {
        return Manufacture.class.getSimpleName();
    }

    @Override
    public Manufacture getEntity() {
        return new Manufacture();
    }
}
