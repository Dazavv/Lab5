package me.dasha.lab5.utility;

import me.dasha.lab5.collectionClasses.Coordinates;
import me.dasha.lab5.collectionClasses.SpaceMarine;
import me.dasha.lab5.parser.readers.*;

import java.time.LocalDateTime;
import java.util.*;
/**
 * this class interacts with the collection
 */
public class CollectionManager {
    private static Stack<SpaceMarine> stack;
    private static LocalDateTime creationDate;

    /**
     * check the existence of the stack
     */
    public static void checkStack() {
        if (stack == null) {
            stack = new Stack<>();
            creationDate = LocalDateTime.now();
        }
    }
    public static boolean checkValidation(SpaceMarine spaceMarine) {
        if (spaceMarine.getId() != null
                && StringReader.readFromJson(spaceMarine.getName(), false)
                && IntReader.readFromJson(spaceMarine.getCoordinates().getX(), false, -623)
                && DoubleReader.readFromJson(spaceMarine.getCoordinates().getY(), false, -347)
                && spaceMarine.getCoordinates().getY() == 0 && spaceMarine.getCoordinates().getX() == 0
                && spaceMarine.getCreationDate() != null
                && DoubleReader.readFromJson(spaceMarine.getHealth(), false, 0)
                && StringReader.readFromJson(spaceMarine.getAchievements(), true)
                && WeaponReader.readFromJson(spaceMarine.getWeaponType(), true)
                && MeleeWeaponReader.readFromJson(spaceMarine.getMeleeWeapon(), true)
                && StringReader.readFromJson(spaceMarine.getChapter().getName(), false)
                && StringReader.readFromJson(spaceMarine.getChapter().getParentLegion(), true)
                && StringReader.readFromJson(spaceMarine.getChapter().getWorld(), true)) {
            return true;
        }
        else {return false;}
    }
    /**
     * get items stack
     * @return stack
     */
    public static Stack<SpaceMarine> getStack() {
        return stack;
    }

    /**
     * add the SpaceMarine class to the collection
     * @param spaceMarine
     */
    public static void add(SpaceMarine spaceMarine) {
        stack.add(spaceMarine);
    }

    /**
     * set id item of the SpaceMarine class
     * @param spaceMarine
     */
    public static void addJSONObject(SpaceMarine spaceMarine) {
        if (checkValidation(spaceMarine)) {
            spaceMarine.setId(GeneratorID.saveId(spaceMarine.getId()));
            stack.add(spaceMarine);
        }
        else {
            System.out.println("Какой-то из параметров не подходит/отсутствует\nДля запуска приложения исправьте файл JSON");
            System.exit(0);
        }

    }
    /**
     * get information stack collection
     */
    public static void info() {
        System.out.print("-----------------------------------"
                + "\nТип коллекции: " + stack.getClass().getName()
                + "\nДата инициализации: " + creationDate
                + "\nКоличество элементов в коллекции: " + stack.size()
                + "\n-----------------------------------\n");
    }
    /**
     * output all elements of the collection in string representation
     */
    public static void show() {
        if (stack.isEmpty()) {
            System.out.println("Коллекция пустая");
        }
        stack.forEach(System.out::println);
    }
    /**
     * update the id of the element whose value is equal to the given one
     * @param spaceMarine
     * @param id
     */
    public static void update(SpaceMarine spaceMarine, Integer id) {
        stack.forEach(SpaceMarine -> {
            if (SpaceMarine.getId().equals(id)) {
                SpaceMarine.setName(spaceMarine.getName());
                SpaceMarine.setCoordinates(spaceMarine.getCoordinates());
                SpaceMarine.setHealth(spaceMarine.getHealth());
                SpaceMarine.setAchievements(spaceMarine.getAchievements());
                SpaceMarine.setWeaponType(spaceMarine.getWeaponType());
                SpaceMarine.setMeleeWeapon(spaceMarine.getMeleeWeapon());
                SpaceMarine.setChapter(spaceMarine.getChapter());
            }
        });
    }
    /**
     * remove an item from the collection by id
     * @param id
     */
    public static void removeById(Integer id) {
        boolean isRemoved = false;
        Iterator<SpaceMarine> i = stack.iterator();
        while (i.hasNext()) {
            SpaceMarine spaceMarine = i.next();
            {
                if (spaceMarine.getId().equals(id)) {
                    i.remove();
                    isRemoved = true;
                }
            }
        }
        if (isRemoved){
            System.out.println("Элемента удален");
        }
        else {
            System.out.println("Элемента с таким ID нет в коллекции");
        }
    }
    /**
     * clear stack collection
     */
    public static void clear() {
        stack.clear();
        GeneratorID.clearSet();
    }
    /**
     * add b new element to b given position
     * @param index
     * @param element
     */
    public static void insertAt(int index, SpaceMarine element) {
        Stack<SpaceMarine> newStack = new Stack<>();
        int count = 0;
        while (!stack.isEmpty() && count < index) {
            newStack.push(stack.pop());
            count++;
        }
        stack.push(element);
        while (!newStack.isEmpty()) {
            stack.push(newStack.pop());
        }
    }
    /**
     * add b new element if its value is less than the smallest element of this collection
     * @param spaceMarine
     */
    public static void addIfMin(SpaceMarine spaceMarine) {
        SpaceMarineComparator comparator = new SpaceMarineComparator();
        for (SpaceMarine spaceMarine1 : stack) {
            if (comparator.compare(spaceMarine1, spaceMarine) == 1) {
                stack.add(spaceMarine1);
            } else {
                System.out.println("Невозможно добавить элемент");
                break;
            }
        }
    }
    /**
     * remove from the collection one item whose achievements field value is equivalent to the given one
     * @param achievements - field item of the class SpaceMarine
     */
    public static void removeAnyByAchievements(String achievements) {

        boolean isRemoved = false;
        Iterator<SpaceMarine> i = stack.iterator();
        while (i.hasNext()) {
            SpaceMarine spaceMarine = i.next();
            {
                if (spaceMarine.getAchievements().equals(achievements)) {
                    i.remove();
                    isRemoved = true;
                }
            }
        }
        if (isRemoved){
            System.out.println("Элемент удален из коллекции");
        }
        else {
            System.out.println("Элемента с таким achievement нет в коллекции");
        }
    }
    /**
     * sort items in natural order
     */
    public static void sort() {
        if (stack.empty()) {
            System.out.println("Коллекция пустая. Сортировка невозможна.");
        } else {
            Stack<SpaceMarine> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                SpaceMarine topInputStack = stack.pop();
                while (!tempStack.empty() && tempStack.peek().compareTo(topInputStack) > 0) {
                    SpaceMarine topTempStack = tempStack.pop();
                    stack.push(topTempStack);
                }
                tempStack.push(topInputStack);
            }
            stack = tempStack;
            System.out.println("Коллекция отсортирована");
        }
    }

    /**
     * group the elements by coordinates value and print the number of elements in each group
     */
    public static void groupCountingByCoordinates() {
        Map<Coordinates, Integer> countMap = new HashMap<>();
        for (SpaceMarine item : stack) {
            Coordinates coordinates = item.getCoordinates();
            countMap.put(coordinates, countMap.getOrDefault(coordinates, 0) + 1);
        }

        for (SpaceMarine item : stack) {
            Coordinates coordinates = item.getCoordinates();
            if (countMap.get(coordinates) > 0) {
                System.out.println("Координата X: " + coordinates.getX() +
                        ", координата Y: " + coordinates.getY() +
                        " - количество: " + countMap.get(coordinates));
                countMap.put(coordinates, 0);
            }
        }
    }
}
