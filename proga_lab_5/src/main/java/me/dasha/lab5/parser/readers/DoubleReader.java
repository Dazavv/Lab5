package me.dasha.lab5.parser.readers;

import java.util.Scanner;
/**
 * this class reading a user-entered value of double type
 */
public class DoubleReader {
    public static double read(String msg, boolean canBeNull, int limit) {
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        double result;
        while (true) {
            try {
                result = Double.parseDouble(scanner.nextLine().trim());
                //считываем значение с помощью метода scanner.nextLine().trim(),
                // затем преобразовать к типу double с помощью метода Double.parseDouble
                if (result > limit) {
                    break;
                }
                System.out.println("Недопустимое значение. Попробуйте снова: ");
            }
            catch (NumberFormatException ex) {
                if (canBeNull && scanner.nextLine().trim().equals("")) {
                    return -1;
                }
                System.out.println("Недопустимое значение. Введите число: ");
            }
        }
        return result;
    }
}
