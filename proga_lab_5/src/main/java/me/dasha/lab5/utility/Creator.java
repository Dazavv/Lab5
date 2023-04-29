package me.dasha.lab5.utility;

import me.dasha.lab5.collectionClasses.*;
import me.dasha.lab5.parser.readers.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * this class creates class SpaceMarine in collection
 */
public class Creator {
    public static SpaceMarine createSpaceMarine() {
        String name = StringReader.read("Введите имя: ", false);
        int x = IntReader.read("Введите координату X: ", false, -623);
        double y = DoubleReader.read("Введите координату Y: ", false, -347);
        double health = DoubleReader.read("Введите значение здоровья: ", false, 0);
        String achievements = StringReader.read("Введите достижение: ", true);
        Weapon weaponType = WeaponReader.read(true);
        MeleeWeapon meleeWeapon = MeleeWeaponReader.read(true);
        String chapterName = StringReader.read("Введите имя главы: ", false);
        String parentLegion = StringReader.read("Введите родительский легион: ", true);
        String world = StringReader.read("Введите мир: ", true);
        return new SpaceMarine(GeneratorID.generateID(), name, new Coordinates(x, y), LocalDateTime.now(), health, achievements, weaponType, meleeWeapon, new Chapter(chapterName, parentLegion, world));
    }
    //метод validateArray проверяет корректность переданных значений в виде ArrayList
    /**
     * check the correctness of the transmitted values
     * @param parameters - fields items of class SpaceMarine
     * @return true or false
     */
    private static boolean validateArray (ArrayList<String> parameters) {
        try {
            return (!parameters.get(0).isEmpty() && !parameters.get(1).isEmpty() && !parameters.get(2).isEmpty()
                    && !parameters.get(3).isEmpty() && !parameters.get(4).isEmpty() && !parameters.get(5).isEmpty()
                    && !parameters.get(6).isEmpty() || parameters.get(6).isEmpty()
                    && (WeaponReader.checkExist(parameters.get(7)) || parameters.get(7).isEmpty())//weapon
                    && (MeleeWeaponReader.checkExist(parameters.get(8)) || parameters.get(8).isEmpty())//meleeweapon
                    && !parameters.get(9).isEmpty() && !parameters.get(10).isEmpty()
                    && !parameters.get(11).isEmpty() || parameters.get(11).isEmpty());
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    public static SpaceMarine ScriptSpaceMarine(ArrayList<String> parameters) {
        if (validateArray(parameters)) {
            Weapon weapon = null;
            MeleeWeapon meleeWeapon = null;
            if (!parameters.get(7).isEmpty()) {
                weapon = Weapon.valueOf(parameters.get(7));
            }
            if (!parameters.get(8).isEmpty()) {
                meleeWeapon = MeleeWeapon.valueOf(parameters.get(8));
            }
            return new SpaceMarine(Integer.parseInt(parameters.get(0)), parameters.get(1),
                    new Coordinates(Integer.parseInt(parameters.get(2)), Double.parseDouble(parameters.get(3))),
                    LocalDateTime.parse(parameters.get(4)), Double.parseDouble(parameters.get(5)), parameters.get(6), weapon, meleeWeapon,
                    new Chapter(parameters.get(9), parameters.get(10), parameters.get(11)));
        }
        else {
            System.out.println("Какое-то значение не соответствует требованиям");
        }
        return null;
    }




}
