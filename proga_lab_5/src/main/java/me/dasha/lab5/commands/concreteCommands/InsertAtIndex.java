package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class adds a new element to a given position
 */
public class InsertAtIndex extends Command {
    private final CommandReceiver commandReceiver;

    public InsertAtIndex (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            commandReceiver.insertAt(args[1]);
        }
        else {
            System.out.println("Некорректное количество аргументов. Для справки напишите help."); }

    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo(){
        System.out.println("Команда insert_at добавить новый элемент в заданную позицию");
    }
}
