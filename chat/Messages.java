package chat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Messages {
    private final File fileMessages = new File("src\\messages.txt");

    /**
     * Получить относительный путь файла
     * @return значение свойства fileMessages
     */
    public File getFileMessages() {
        return fileMessages;
    }

    /**
     * Получить абсолютный путь файла
     * @return абсолютный путь к файлу
     */
    public Path getAbsolutePathFileMessages() {
        return Path.of(String.valueOf(fileMessages)).toAbsolutePath();
    }

    /**
     * Перезаписываем данные файла fileMessages
     * @param builder - список сообщений
     * @param login - логин + дата + сообщение, введенные пользоватлем
     */
    public void rewriteFileMessages(StringBuilder builder, String login) {
        builder.append(login);
        try {
            Files.writeString(getAbsolutePathFileMessages(), builder);
        } catch (IOException e) {
            System.out.println("Путь к файлу " + getFileMessages() + " не найден.");
            // выход из программы
            System.exit(0);
        }
    }
}
