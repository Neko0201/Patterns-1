package ru.netology.replandelivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.replandelivery.data.DataGenerator;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ReplanDeliveryTests {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void sendingFormTest() {
        var user = DataGenerator.Registration.generateUser("ru");
        var firstDateMeeting = DataGenerator.generateDate(3, "dd.MM.yyyy");
        var seconddateMeeting = DataGenerator.generateDate(10, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstDateMeeting);
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").findBy(text("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Встреча успешно запланирована на")).shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(text("Встреча успешно запланирована на " + firstDateMeeting));
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(seconddateMeeting);
        $$("button").findBy(text("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Встреча успешно запланирована на")).shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(text("Встреча успешно запланирована на " + seconddateMeeting));
    }
}