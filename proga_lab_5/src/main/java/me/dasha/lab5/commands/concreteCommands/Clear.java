package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class clear the collection
 */
public class Clear extends Command {
    private final CommandReceiver commandReceiver;

    public Clear(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде clear.");
        }
        commandReceiver.clear();
    }

    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда clear очищает коллекцию.");
    }
}