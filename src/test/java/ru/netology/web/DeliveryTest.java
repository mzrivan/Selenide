package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(serviceDate.dateAfterDays(5));
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement] span").click();
        $("[role=button].button").click();
        $("[data-test-id=notification]").shouldHave(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldDeliveryCityChoicePositiveTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Бе");
        $(byText("Белгород")).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(serviceDate.dateAfterDays(5));
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement] span").click();
        $("[role=button].button").click();
        $("[data-test-id=notification]").shouldHave(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldDeliveryCalendarChoicePositiveTest() {
        open("http://localhost:9999");
        int afterDay = 385;
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
        $("[data-test-id=notification]").shouldHave(visible, Duration.ofSeconds(15));
    }

}



