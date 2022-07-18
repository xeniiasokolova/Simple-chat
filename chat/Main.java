package chat;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Messages messages = new Messages();
        Users users = new Users();
        Scanner console = new Scanner(System.in);
        System.out.println("Войти в чат? (да/нет)");

        if (console.nextLine().equalsIgnoreCase("нет")) {
            console.close();
        } else {
            String login = "";
            while (!login.equalsIgnoreCase("выход")) {
                System.out.println("Введите логин пользователя. Для выхода напишите \"выход\"");
                login = console.nextLine();

                if (login.equalsIgnoreCase("выход"))
                    continue;

                var builderMessages = getBuilderFile(messages.getFileMessages());
                var builderUsers = getBuilderFile(users.getFileUsers());

                //если логина нет в списке, обновляем список логинов в файле users.txt
                if (!users.checkUsers(login))
                    users.rewriteFileUsers(builderUsers, login);

                System.out.print(builderMessages + "\n" + login + " ведите ваше сообщение: ");

                //обновляем список сообщений в файле
                String localDate = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(LocalDateTime.now());
                messages.rewriteFileMessages(builderMessages, "[" + login + ", " +
                        localDate + "]\t" + console.nextLine());
            }
        }
        console.close();
    }

    /**
     * Получаем данные из файла
     * @param file - отонсительный путь к файлу
     * @return - данные из файла
     */
    public static StringBuilder getBuilderFile(File file) {
        var builder = new StringBuilder();
        try (var sc = new Scanner(file)) {
            while (sc.hasNext()) {
                var str = sc.nextLine();
                builder.append(str).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Путь к файлу " + file.getName() + " не найден.");
            // выход из программы
            System.exit(0);
        }
        return builder;
    }
}
