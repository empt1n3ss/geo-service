package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    @Test
    void test_locale_return_text_ru(){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl()
                ;
        String result = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", result);
    }
    @Test
    void test_locale_return_text_default(){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String result = localizationService.locale(Country.BRAZIL);

        assertEquals("Welcome", result);
    }
}
