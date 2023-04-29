package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class output command information
 */
public class Help extends Command {
    private final CommandReceiver commandReceiver;

    public Help (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде help.");
        }
        commandReceiver.help();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo(){
        System.out.println("Команда help выводит справку о доступных командах");
    }
}
