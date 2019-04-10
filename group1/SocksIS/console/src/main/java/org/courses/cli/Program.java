package org.courses.cli;

import org.courses.cli.commands.Command;
import org.courses.cli.commands.CommandFormatException;
import org.apache.tools.ant.types.Commandline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;
import java.util.Scanner;

public class Program {
    //log4J or slf4j use 'info':  for debugging
    static Map<String, Command> commands;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(org.courses.cli.SpringConfig.class);
        commands = (Map<String, Command>) context.getBean("commands");
        Scanner scanner = context.getBean(Scanner.class);

        greetUser();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            parseUserInput(line);
            greetUser();
        }
    }

    private static void parseUserInput(String input) {
        String userCommand[] = Commandline.translateCommandline(input);

        String commandName = userCommand[0];
        String[] params = new String[userCommand.length - 1];
        System.arraycopy(userCommand, 1, params, 0, params.length);

        try {
            executeCommand(commandName, params);
        } catch (CommandFormatException e) {
            System.out.println(String.format("%s has some invalid arguments", commandName));
        } catch (RuntimeException e) {
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
