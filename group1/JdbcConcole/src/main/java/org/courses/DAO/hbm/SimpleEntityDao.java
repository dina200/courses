package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.SimpleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public abstract class SimpleEntityDao<S extends SimpleEntity> implements DAO<S, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();
    private SessionFactory factory;

    SimpleEntityDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void save(Collection<S> entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            saveTypes(session, transaction, entity);
        } catch (Exception e) {
            if (null != transaction)
                transaction.rollback();
            throw e;
        } finally {
            if (null != session)
                session.close();
        }
    }

    private void saveTypes(Session session, Transaction transaction, Collection<S> entity) {
        for (S s : entity) {
            session.save(s);
        }
        transaction.commit();
    }

    @Override
    public S read(Integer id) {
        S result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session.find(getEntityClass(), id);
        } finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    protected abstract Class<S> getEntityClass();

    @Override
    public Collection<S> readAll() {
        Collection<S> result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from " + getEntityName())
                    .list();
        } finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    protected abstract String getEntityName();

    @Override
    public Collection<S> find(String filter) {
        List result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from  " + getEntityName() +
                            "where id = :id " +
                            "or name like :filter")
                    .setParameter("id", Int32.validate(filter))
                    .setParameter("filter", String.format("%%%s%%", filter))
                    .list();
        } finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public void delete(S entity) {
        Session session = null;
        try {
            session = factory.openSession();
            session.delete(entity);
        } finally {
            if (null != session)
                session.close();
        }
    }
}
