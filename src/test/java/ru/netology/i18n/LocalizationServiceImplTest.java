package ru.netology.i18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    @BeforeAll
    public static void start() {
        System.out.println("***Все тесты стартовали***");
    }

    @BeforeEach
    public void startEach() {
        System.out.println("Старт теста");
    }

    @AfterEach
    public void finishEach() {
        System.out.println("Завершение теста");
    }

    @AfterAll
    static void finish() {
        System.out.println("***Все тесты завершились***");
    }

    @ParameterizedTest
    @MethodSource("source2")
    public void localizationTest(Country country, String hi) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(country);
        assertEquals(hi, result);
    }

    private static Stream<Arguments> source2() {
        return Stream.of(Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"));
    }
}