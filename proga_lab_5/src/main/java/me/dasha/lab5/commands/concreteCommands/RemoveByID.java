package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class remove an item from the collection by id
 */
public class RemoveByID extends Command {
    private final CommandReceiver commandReceiver;

    public RemoveByID (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            commandReceiver.removeById(args[1]);
        }
        else {
            System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда remove_by_id удаляет элемент из коллекции по его id.");
    }
}