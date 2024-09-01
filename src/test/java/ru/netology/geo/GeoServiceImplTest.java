package ru.netology.geo;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {
    GeoService geoServiceImpl = new GeoServiceImpl();

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
    @MethodSource("source")
    public void GeoServiceImplTest(String ip, @org.jetbrains.annotations.NotNull Location expected) {
        Location result = geoServiceImpl.byIp(ip);
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
    }

    private static @NotNull Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }
}