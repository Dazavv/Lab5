package me.dasha.lab5.commands;
/**
 * this abstract class for creation commands
 */
public abstract class Command {
    /**
     * output information about command
     */
    public abstract void writeInfo();
    /**
     * execute command
     * @param args - command
     */
    public abstract void execute(String[] args);

}