package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;

/**
 * this class reads and executes a script from a specified file
 */
public class ExecuteScript extends Command {
    private final CommandReceiver commandReceiver;
    private static String path;

    public ExecuteScript(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) throws StackOverflowError {
        try {
            if (args.length == 2) { path = args[1]; commandReceiver.executeScript(args[1]); }
            else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка! Обнаружен выход за пределы стека.");
        }
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда execute_script file_name считывает и исполняет скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    public static String getPath() {
        return path;
    }
}