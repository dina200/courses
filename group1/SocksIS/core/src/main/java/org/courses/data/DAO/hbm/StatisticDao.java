package org.courses.data.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.domain.hbm.Statistic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;

public class StatisticDao extends BaseDao<Statistic, Integer> {
    private IntegerValidator Int32 = IntegerValidator.getInstance();

    public StatisticDao(SessionFactory factory) {
        super(factory, Statistic.class);
    }

    @Override
    public Collection<Statistic> find(String filter) {
        Session session = factory.getCurrentSession();
        return session
                .createQuery("from Statistic " +
                        "where id = :id ")
                .setParameter("id", Int32.validate(filter))
                .list();
    }
}
