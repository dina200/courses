package org.courses;

import org.apache.tools.ant.types.Commandline;
import org.courses.DAO.entities.Type;
import org.courses.DAO.operations.TypeOperation;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.commands.jdbc.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Program {
    private static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("connect", new CreateDb());
        commands.put("table", new CreateTable());
        commands.put("addtype", new AddTypeCommand());
        commands.put("addmaterial", new AddMaterialCommand());
        commands.put("addmanufacture", new AddManufactureCommand());
        commands.put("listmaterial", new ListMaterialCommand());
        commands.put("listtype", new ListTypeCommand());
        commands.put("listmanufacture", new ListManufactureCommand());
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);

//        greetUser();
//        while (scanner.hasNext()) {
//            String line = scanner.nextLine();
//            parseUserInput(line);
//            greetUser();
//        }
        Connection con = ConnectionManager.getConnection("test.db");
        TypeOperation operation = new TypeOperation(con);
        try {
            System.out.println(operation.ReadAll());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void parseUserInput(String input) {
        String[] userCommand = Commandline.translateCommandline(input);

        String commandName = userCommand[0];
        String[] params = new String[userCommand.length - 1];
        System.arraycopy(userCommand, 1, params, 0, params.length);

        try {
            executeCommand(commandName, params);
        } catch (CommandFormatException e) {
            System.out.println(String.format("%s has some invalid arguments", commandName));
        } catch (RuntimeException e) { // Error: package sun.reflect.generics.reflectiveObjects does not exist; java: cannot find class NotImplementedException
            System.out.println(String.format("Unknown command %s", commandName));
        }
    }

    private static void executeCommand(String commandName, String[] params) {
        Command command = commands.get(commandName);

        if (null == command)
            throw new RuntimeException();

        command.parse(params);
        command.execute();
    }

    private static void greetUser() {
        System.out.print("courses-jdbc>");
    }
}
