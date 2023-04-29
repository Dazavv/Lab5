package me.dasha.lab5.commands;

import me.dasha.lab5.utility.CollectionManager;
import me.dasha.lab5.utility.Utils;
import me.dasha.lab5.collectionClasses.SpaceMarine;
import me.dasha.lab5.parser.Parser;
import me.dasha.lab5.utility.Creator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static me.dasha.lab5.commands.concreteCommands.ExecuteScript.getPath;
/**
 * this class accepts the command and contains the basic logic of the commands
 */
public class CommandReceiver {
    private final CommandInvoker commandInvoker;
    private SpaceMarine spaceMarine;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }
    /**
     * command information output
     */
    public void help() {
        System.out.println("help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "add {element} : добавить новый элемент в коллекцию \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл \n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме \n" +
                "exit : завершить программу (без сохранения в файл) \n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию \n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции \n" +
                "sort : отсортировать коллекцию в естественном порядке \n" +
                "remove_any_by_achievements achievements : удалить из коллекции один элемент, значение поля achievements которого эквивалентно заданному \n" +
                "group_counting_by_coordinates : сгруппировать элементы коллекции по значению поля coordinates, вывести количество элементов в каждой группе \n"
        );
    }
    /**
     * output information about collection
     */
    public void info() {
        CollectionManager.info();
    }
    /**
     * output all elements of the collection in string representation
     */
    public void show() {
        CollectionManager.show();
    }
    /**
     * add item to collection
     */
    public void add() {
        CollectionManager.add(Creator.createSpaceMarine());
        System.out.println("Элемент добавлен в коллекцию");
    }
    /**
     * update the id of the element whose value is equal to the given one
     * @param ID - collection item id field
     */
    public void update(String ID) {
        Integer id;
        try {
            id = Integer.parseInt(ID);
            if (Utils.checkExist(id)) {
                CollectionManager.update(Creator.createSpaceMarine(), id);
                System.out.println("Элемент с id " + id + " обновлён");
            } else {
                System.out.println("Элемента с таким id нет в коллекции");
            }
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент");
        }
    }
    /**
     * remove an item from the collection by id
     * @param ID - collection item id field
     */
    public void removeById(String ID) {
        Integer id;
        try {
            id = Integer.parseInt(ID);
            CollectionManager.removeById(id);
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент");
        }
    }
    /**
     * clear the collection
     */
    public void clear() {
        CollectionManager.clear();
        System.out.println("Коллекция успешно очищена");
    }
    /**
     * saves the collection to a file
     */
    public void save() {
        Parser.collectionToJson();
    }
    /**
     * reads and executes a script from a specified file
     * @param path - collection path
     */
    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|")) {
                    command = line;
                    for (int i = 0; i < 15; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else {
                            System.out.println("Не хватает параметров для создания объекта.");
                            break;
                        }
                    }
                    SpaceMarine product = Creator.ScriptSpaceMarine(parameters);
                    switch (command.split(" ")[0]) {
                        case "add":
                            CollectionManager.add(product);
                            break;
                        case "update":
                            CollectionManager.update(product, Integer.parseInt(command.split(" ")[1]));
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(getPath())) {
                    System.out.println("Пресечена попытка рекурсивного вызова скрипта.");
                } else {
                    commandInvoker.executeCommand(line.split(" "));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }
    /**
     * end the program
     */
    public void exit() {
        System.out.println("Завершение работы программы (без сохранения в файл)");
        System.exit(0);
    }
    /**
     * add a new element to a given position
     * @param ind - item index
     */
    public void insertAt(String ind) {
        Integer index;
        try {
            index = Integer.parseInt(ind);
            CollectionManager.insertAt(index, Creator.createSpaceMarine());
            System.out.println("Команда выполнена");
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент");
        }
    }
    /**
     * add a new element if its value is less than the smallest element of this collection
     */
    public void addIfMin() {
        CollectionManager.addIfMin(Creator.createSpaceMarine());
    }
    /**
     * sort items in natural order
     */
    public void sort() {
        CollectionManager.sort();
    }
    /**
     * remove from the collection one item whose achievements field value is equivalent to the given one
     * @param achievements - item "achievements" field
     */

    public void removeAnyByAchievements(String achievements) {
        CollectionManager.removeAnyByAchievements(achievements);
    }
    /**
     * group the elements by coordinates value and print the number of elements in each group
     */
    public void groupCountingByCoordinates() {
        if (!CollectionManager.getStack().isEmpty()) {
            CollectionManager.groupCountingByCoordinates();
        }
        else {
            System.out.println("Не удается выполнить команду. Коллекция пуста");
        }
    }
}

