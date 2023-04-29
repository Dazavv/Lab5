package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class output all elements of the collection in string representation
 */
public class Show extends Command {
    private final CommandReceiver commandReceiver;

    public Show (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде show.");
        }
        commandReceiver.show();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда show – вывести все элементы коллекции в строковом представлении.");
    }
}