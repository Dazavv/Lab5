package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class saves the collection to a file
 */
public class Save extends Command {
    private final CommandReceiver commandReceiver;

    public Save(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде save.");
        }
        commandReceiver.save();
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда save сохраняет коллекцию в файл.");
    }
}