package me.dasha.lab5.parser.readers;

import java.util.Scanner;
/**
 * this class reading a user-entered value of string type
 */
public class StringReader {
    public static String read(String msg, boolean canBeNull) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        String readStr = scanner.nextLine().trim();

        while (!canBeNull && readStr.isEmpty() || !readStr.matches("^[a-zA-Z-А-Яа-я]*$")) {
            System.out.println("Поле должно состоять из букв. " + msg);
            readStr = scanner.nextLine().trim();
        }
        if (canBeNull && readStr.isEmpty()) {
            readStr = null;
        }
        return readStr;
    }
}
