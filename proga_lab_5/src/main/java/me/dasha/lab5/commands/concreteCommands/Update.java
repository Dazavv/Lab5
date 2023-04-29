package me.dasha.lab5.commands.concreteCommands;

import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class update the id of the element whose value is equal to the given one
 */
public class Update extends Command {
    private final CommandReceiver commandReceiver;

    public Update (CommandReceiver commandReceiver) {

        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            commandReceiver.update(args[1]);
        }
        else {
            System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда update обновляет значение элемента коллекции, id которого равен заданному.");
    }
}