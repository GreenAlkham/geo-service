package ru.netology.sender;

import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderImplTest {

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
        System.out.println("\nЗавершение теста");
    }

    @AfterAll
    static void finish() {
        System.out.println("***Все тесты завершились***");
    }

    @Test
    public void messageSenderRussianTest() {
        Map<String, String> map = new HashMap<>();
        map.put("x-real-ip", "172.0.32.11");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(map);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void messageSenderEnglishTest() {
        Map<String, String> map = new HashMap<>();
        map.put("x-real-ip", "96.44.183.149");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String res = messageSender.send(map);

        assertEquals("Welcome", res);
    }

    @Test()
    void testExpectedException() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            GeoServiceImpl exception = new GeoServiceImpl();
            exception.byCoordinates(1, 1);
        });
        Assertions.assertEquals("Not implemented", thrown.getMessage());
    }
}