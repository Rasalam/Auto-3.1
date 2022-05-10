package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DebitCardApplicationTest {

    @BeforeEach
    public void prepareBrowser() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnPositiveResponse() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldShowMassageIfNameIsEmpty() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowMassageIfNameEnteredIncorrectly() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Valentina Petrovna");
        form.$("[data-test-id=phone] input").setValue("+70000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowMassageIfPhoneIsEmpty() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowMassageIfPhoneEnteredIncorrectly() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Валентина Петровна");
        form.$("[data-test-id=phone] input").setValue("5465564");
        form.$("[data-test-id=agreement]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldChangeCaptionColorToRed() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Влентина Петровна");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

