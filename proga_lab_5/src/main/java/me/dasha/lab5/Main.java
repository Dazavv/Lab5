package me.dasha.lab5;
/**
 * @author Olga and Darya
 * @version 1.0
 */

import me.dasha.lab5.parser.Parser;
import me.dasha.lab5.utility.ConsoleManager;
import java.io.IOException;

/**
 * this class represents the main entry point for the ConsoleApp
 */

public class Main {

    /**
     * This is start point of the program
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        try {
            Parser.fromJsonToCollection(args[0]);
            System.out.println("Введите команду. Для справки введите \"help\"");
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.start();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы не указали имя файла\nПриложение не может запуститься");
        }
        catch (IllegalStateException e) {
            System.out.println("Ошибка в содержании файла\nПриложение не может запуститься");
            System.exit(0);
        }
    }
}