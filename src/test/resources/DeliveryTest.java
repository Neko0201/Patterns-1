package ru.netology.delivery.test;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
//        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        LocalDate currentDate = LocalDate.now();
        LocalDate date = currentDate.plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        return formattedDate;
    }

    public static String generateCity() {
        String[] cities = new String[]{"Москва", "Санкт-Петербург", "Севастополь", "Абакан",
                "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород",
                "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород", "Владивосток",
                "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Гатчина",
                "Горно-Алтайск", "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Иркутск",
                "Йошкар-Ола", "Казань", "Калининград"};
        Random random = new Random();
        int randomIndex = random.nextInt(cities.length);

        return cities[randomIndex];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String fullName = lastName + " " + firstName;

        return fullName;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}