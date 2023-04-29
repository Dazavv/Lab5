package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class adds a new element if its value is less than the smallest element of this collection
 */
public class AddIfMin extends Command {
    private final CommandReceiver commandReceiver;

    public AddIfMin(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде add_if_min.");
        }
        commandReceiver.addIfMin();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда add_if_min - добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.");
    }
}