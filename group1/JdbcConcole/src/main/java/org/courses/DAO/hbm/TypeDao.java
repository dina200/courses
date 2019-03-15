package org.courses.DAO.hbm;

import org.courses.domain.hbm.SimpleEntity;
import org.courses.domain.hbm.Type;
import org.hibernate.SessionFactory;

public class TypeDao extends SimpleEntityDao<Type> {
    public TypeDao(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Class<Type> getEntityClass() {
        return Type.class;
    }

    @Override
    public String getEntityName() {
        return "Type";
    }


    @Override
    public Type getEntity() {
        return new Type();
    }
}
