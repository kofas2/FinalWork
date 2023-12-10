package FinalWork;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол):");
            String input = scanner.nextLine();

            String[] data = input.split("\\s+");
            if (data.length != 6) {
                throw new InvalidDataFormatException("Неверное количество данных. Введите все данные в указанном порядке.");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            saveUserDataToFile(userData);

            System.out.println("Данные успешно сохранены в файл.");
        } catch (InvalidDataFormatException | NumberFormatException e) {
            System.err.println("Ошибка в формате введенных данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void saveUserDataToFile(UserData userData) throws IOException {
        String fileName = userData.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userData.toString());
            writer.newLine();
        }
    }
}
