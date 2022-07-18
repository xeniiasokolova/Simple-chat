package chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Users {
    private final File fileUsers = new File("src\\users.txt");

    /**
     * Получить относительный путь файла
     * @return значение свойства fileUsers
     */
    public File getFileUsers() {
        return fileUsers;
    }

    /**
     * Получить абсолютный путь файла
     * @return абсолютный путь к файлу
     */
    public Path getAbsolutePathFileUsers() {
        return Path.of(String.valueOf(fileUsers)).toAbsolutePath();
    }

    /**
     * Проверка наличия пользователя в файле
     * @param login - логин пользователя
     * @return - если пользователь есть в списке - true, если его нет - false
     */
    public boolean checkUsers(String login) {
        boolean isTrue = false;
        try (var sc = new Scanner(fileUsers)) {
            while (sc.hasNext()) {
                var str = sc.nextLine();
                if (str.equals(login))
                    isTrue = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Путь к файлу " + getFileUsers() + " не найден.");
            // выход из программы
            System.exit(0);
        }
        return isTrue;
    }

    /**
     * Перезаписываем данные файла fileUsers
     * @param builder - список пользователей
     * @param login - логин пользоватлем
     */
    public void rewriteFileUsers(StringBuilder builder, String login) {
        builder.append(login);
        try {
            Files.writeString(getAbsolutePathFileUsers(), builder);
        } catch (IOException e) {
            System.out.println("Путь к файлу " + getFileUsers() + " не найден.");
            //выход из программы
            System.exit(0);
        }
    }
}
