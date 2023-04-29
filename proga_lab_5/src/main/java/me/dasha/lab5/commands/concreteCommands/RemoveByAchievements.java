package me.dasha.lab5.commands.concreteCommands;


import me.dasha.lab5.commands.Command;
import me.dasha.lab5.commands.CommandReceiver;
/**
 * this class removes from the collection one item whose achievements field value is equivalent to the given one
 */
public class RemoveByAchievements extends Command {
    private CommandReceiver commandReceiver;
    public RemoveByAchievements(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    /**
     * execute command
     * @param args - command
     */
    @Override
    public void execute(String[] args) {
        try {
            commandReceiver.removeAnyByAchievements(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            if (!(args.length == 2)) {
                System.out.println("У команды должен быть введен один аргумент");
            }
        }
    }
    /**
     * output information about command
     */
    @Override
    public void writeInfo() {
        System.out.println("Команда remove_by_achievements - удалить из коллекции один элемент, значение поля achievements которого эквивалентно заданному");
    }
}