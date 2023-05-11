package me.dasha.lab5.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.dasha.lab5.utility.CollectionManager;
import me.dasha.lab5.collectionClasses.SpaceMarine;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
    private static String fileName;
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder
            .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                @Override
                public void write(JsonWriter out, LocalDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public LocalDateTime read(JsonReader in) throws IOException {
                    return LocalDateTime.parse(in.nextString());
                }
            })
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();
    /**
     * сonvert collection to json
     */
    public static void collectionToJson() {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bos.write(gson.toJson(CollectionManager.getStack()).getBytes());
            System.out.println("Коллекция успешно сохранена");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * сonvert collection from json
     * @param fileName1
     */
    public static void fromJsonToCollection(String fileName1) {
        Parser.fileName = fileName1;
        if (fileName != null) {
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
                 Reader reader = new InputStreamReader(bufferedInputStream)) {
                CollectionManager.checkStack();
                List<SpaceMarine> spaceMarineList = gson.fromJson(reader, new TypeToken<List<SpaceMarine>>() {}.getType());
                if (spaceMarineList.size() > 0) {
                    for (SpaceMarine spaceMarine : spaceMarineList) {
                        CollectionManager.addJSONObject(spaceMarine);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (SecurityException e) {
                System.out.println("Недостаточно прав для открытия файла");
            } catch (NullPointerException e) {
                System.out.println("В файле нет объектов");
            } catch (JsonSyntaxException | IllegalStateException e) {
                System.out.println("Ошибка в содержании файла " + e.getMessage() + "\nПриложение не может запуститься");
                System.exit(0);
            } catch(DateTimeParseException e){
            System.out.println("Неверный формат даты");
            System.exit(0);
            }
        } else {
            System.out.println("Имя файла не задано");
        }
    }
}
