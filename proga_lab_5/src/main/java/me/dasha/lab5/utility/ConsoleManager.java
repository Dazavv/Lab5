package me.dasha.lab5.utility;

import me.dasha.lab5.commands.CommandInvoker;
import me.dasha.lab5.commands.CommandReceiver;
import me.dasha.lab5.commands.concreteCommands.*;

import java.io.IOException;
import java.util.Scanner;
/**
 * this class manages the interaction between the user and the console
 */
public class ConsoleManager {
    /**
     * console start
     * @throws IOException
     */
    public void start() throws IOException {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        CollectionManager.checkStack();

        commandInvoker.addCommand("help", new Help(commandReceiver));
        commandInvoker.addCommand("info", new Info(commandReceiver));
        commandInvoker.addCommand("show", new Show(commandReceiver));
        commandInvoker.addCommand("add", new Add(commandReceiver));
        commandInvoker.addCommand("update", new Update(commandReceiver));
        commandInvoker.addCommand("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.addCommand("clear", new Clear(commandReceiver));
        commandInvoker.addCommand("save", new Save(commandReceiver));
        commandInvoker.addCommand("execute_script", new ExecuteScript(commandReceiver));
        commandInvoker.addCommand("exit", new Exit(commandReceiver));
        commandInvoker.addCommand("insert_at", new InsertAtIndex(commandReceiver));
        commandInvoker.addCommand("add_if_min", new AddIfMin(commandReceiver));
        commandInvoker.addCommand("sort", new Sort(commandReceiver));
        commandInvoker.addCommand("remove_any_by_achievements", new RemoveByAchievements(commandReceiver));
        commandInvoker.addCommand("group_counting_by_coordinates", new GroupByCoordinates(commandReceiver));
        /**
         * read input commands
         */
        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }
    }

}
