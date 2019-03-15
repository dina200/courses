package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.domain.hbm.SimpleEntity;

import java.util.Collection;

public class ListSimpleEntityCommand<S extends SimpleEntity> implements Command {
    private String filter;
    private DAO<S, Integer> dao;

    public ListSimpleEntityCommand(DAO<S, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            filter = args[0];
        }
        else {
            filter = "";
        }
    }

    @Override
    public void execute() {
        Collection<S> entities;
        if (null == filter || "".equals(filter)) {
            entities = dao.readAll();
        }
        else {
            entities = dao.find(filter);
        }
        for (S s : entities) {
            System.out.println(String.format("%d\t%s", s.getId(), s.getName()));
        }
    }
}