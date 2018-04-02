package livelib.utils;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Arrays;

public class StorageBooks {

    private static ArrayList<String> listOfUsedBooks = new ArrayList<>();
    private static ArrayList<String> listOfBooks = new ArrayList<>(
            Arrays.asList(
                    "Краткая история времени",
                    "Алкоголик в семье или Преодоление созависимости",
                    "Девушка с татуировкой дракона",
                    "Бойцовский клуб",
                    "Java. Полное руководство",
                    "Трое в лодке, не считая собаки",
                    "Хлеб с ветчиной",
                    "Мастер и Маргарита",
                    "Робинзон Крузо",
                    "120 дней Содома, или Школа разврата"
            ));

    public static String getRandomBook() {
        while (true) {
            int i = Utils.getRandomInteger(listOfBooks.size() - 1, 0);
            if (!listOfUsedBooks.contains(listOfBooks.get(i))) {
                listOfUsedBooks.add(listOfBooks.get(i));
                LogManager.getLogger().debug("Book '" + listOfBooks.get(i) + "' was selected for 'Test'");
                return listOfBooks.get(i);
            }
        }
    }
}
