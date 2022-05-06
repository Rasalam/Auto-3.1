package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardApplicationTest {

    @Test
    public void shouldReturnPositiveResponse() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $(".paragraph_theme_alfa-on-white").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowMassageIfNameIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowMassageIfNameEnteredIncorrectly() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Valentina Petrovna");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowMassageIfPhoneIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowMassageIfPhoneEnteredIncorrectly() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("5465564");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldChangeCaptionColorToRed() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Влентина Петровна");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button_theme_alfa-on-white").click();
        String actual = element(".checkbox__text").getCssValue("color");
        String expected = "rgba(255, 92, 92, 1)";
        assertEquals(actual, expected);
    }
}

