package org.courses.cli.commands;

public interface Command {
    void parse(String[] args);

    void execute();
}
