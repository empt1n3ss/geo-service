package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeoServiceImplTest {
    @Test
    void test_by_ip_russian(){
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location location = geoService.byIp("172.0.32.11");

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }
    @Test
    public void testByIp_AmericanSegment() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location location = geoService.byIp("96.44.183.149");

        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertEquals(" 10th Avenue", location.getStreet());
        assertEquals(32, location.getBuiling());
    }
    @Test
    public void testByIp_Localhost() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location location = geoService.byIp("127.0.0.1");

        assertNull(location.getCity());
        assertNull(location.getCountry());
        assertNull(location.getStreet());
        assertEquals(0, location.getBuiling());
    }
}
