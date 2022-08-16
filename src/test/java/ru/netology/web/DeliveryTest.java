package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {
    Date serviceDate = new Date();

    @Test
    void shouldDeliveryPositiveTest() {
        open("http://localhost:9999");
        String planningDate = serviceDate.dateAfterDays(5);
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement] span").click();
        $("[role=button].button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldDeliveryCityChoicePositiveTest() {
        open("http://localhost:9999");
        String planningDate = serviceDate.dateAfterDays(7);
        $("[data-test-id=city] input").setValue("Бе");
        $(byText("Белгород")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement] span").click();
        $("[role=button].button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldDeliveryCalendarChoicePositiveTest() {
        open("http://localhost:9999");
        int afterDay = 7;//Не более 30дней, переключение месяца или года происходит только на один шаг
        String planningDate = serviceDate.dateAfterDays(afterDay);
        $("[data-test-id=city] input").setValue("Воронеж");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        if (serviceDate.isNextYear(afterDay)) {
            $(".calendar__arrow_direction_right[role=button][data-step='12']").click();
        }
        if (serviceDate.isNextMounts(afterDay)) {
            $(".calendar__arrow_direction_right[role=button][data-step='1']").click();
        }
        $$(".calendar__day").find(exactText(serviceDate.dayAfterDays(afterDay))).click();
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement] span").click();
        $("[role=button].button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}



