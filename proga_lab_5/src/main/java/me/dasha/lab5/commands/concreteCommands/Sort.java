package me.dasha.lab5.commands.concreteCommands;


import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class sorts items in natural order
 */
public class Sort extends Command {
    private final CommandReceiver commandReceiver;

    public Sort (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде sort.");
        }
        commandReceiver.sort();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo(){
        System.out.println("Команда sort сортирует элементы в естественном порядке");
    }
}
