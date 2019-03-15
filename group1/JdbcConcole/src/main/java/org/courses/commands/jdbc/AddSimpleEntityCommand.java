package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.SimpleEntity;

import java.util.Arrays;

public class AddSimpleEntityCommand<S extends SimpleEntity> implements Command {
    private String entityName;
    private DAO<S, Integer> dao;

    public AddSimpleEntityCommand(DAO<S, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            entityName = args[0];
        }
        else {
            throw new CommandFormatException("The name is not specified");
        }
    }

    @Override
    public void execute() {
        S m = dao.getEntity();
        m.setName(entityName);
        dao.save(Arrays.asList(m));
    }

}
