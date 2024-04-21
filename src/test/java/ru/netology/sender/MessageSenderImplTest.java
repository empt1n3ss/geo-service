package ru.netology.sender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MessageSenderImplTest {
    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;
    private MessageSenderImpl messageSender;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }
    @Test
    void test_get_russian_text (){
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        when(geoService.byIp("172.0.32.11")).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
    }
    @Test
    void test_get_english_text() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Location location = new Location("New York", Country.USA, "10th Avenue", 32);
        when(geoService.byIp("96.44.183.149")).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String result = messageSender.send(headers);

        assertEquals("Welcome", result);
    }

    @Test
    void test_default_locale() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.LOCALHOST);
        Location location = new Location("Localhost", Country.RUSSIA, "Unknown", 0);
        when(geoService.byIp(GeoServiceImpl.LOCALHOST)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
    }

}

