package me.dasha.lab5.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * this class stores the registered commands and calls them
 */
public class CommandInvoker {
    private HashMap<String, Command> commands = new HashMap<>();
    private List<String> history = new ArrayList<String>();

    /**
     * add command
     * @param commandName
     * @param command
     */
    public void addCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }
    /**
     * command input
     * @param commandName
     */
    public void executeCommand(String[] commandName) {
        try {
            if (commandName.length > 0) {
                history.add(commandName[0]);
                Command command = commands.get(commandName[0]);
                command.execute(commandName);
                System.out.println("Введите команду. Для справки введите \"help\"");
            } else { System.out.println("Вы не ввели команду."); }
        } catch (IllegalStateException | NullPointerException ex) {
            System.out.println("Команда введена неверно. Для справки используйте - help");
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
