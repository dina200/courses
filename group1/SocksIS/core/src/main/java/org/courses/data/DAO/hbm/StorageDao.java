package org.courses.data.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.domain.hbm.Storage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;

public class StorageDao extends BaseDao<Storage, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();

    public StorageDao(SessionFactory factory) {
        super(factory, Storage.class);
    }

    @Override
    public Collection<Storage> find(String filter) {
        Session session = factory.getCurrentSession();
        return session
                .createQuery("from Storage " +
                        "where id = :id " +
                        "or name like :filter")
                .setParameter("id", Int32.validate(filter))
                .setParameter("filter", String.format("%%%s%%", filter))
                .list();
    }
}
