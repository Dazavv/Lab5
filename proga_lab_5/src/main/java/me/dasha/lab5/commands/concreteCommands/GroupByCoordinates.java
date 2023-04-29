package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class groups the elements by coordinates value and prints the number of elements in each group
 */
public class GroupByCoordinates extends Command {
    private final CommandReceiver commandReceiver;

    public GroupByCoordinates(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент");
        }
        commandReceiver.groupCountingByCoordinates();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("group_counting_by_coordinates - сгруппировать элементы по значению coordinates, вывести количество элементов в каждой группе");
    }
}
