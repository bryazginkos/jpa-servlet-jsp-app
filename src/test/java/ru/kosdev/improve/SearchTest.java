package ru.kosdev.improve;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.kosdev.improve.config.TestConfig;
import ru.kosdev.improve.dao.ProductDao;
import ru.kosdev.improve.service.SearchService;
import ru.kosdev.improve.service.Validator;

import static org.mockito.Mockito.*;

/**
 * Created by Kos on 06.09.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, SearchTest.LocalConfig.class})
public class SearchTest {

    private static final String MAX_PRICE = "12.34";
    private static final String MIN_PRICE = "0";
    private static final String PRODUCT = "product";
    private static final String CATEGORY = "category";
    private static final String ERROR = "error";

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private Validator validator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void resetMocks() {
        reset(validator, productDao);
    }

    @Test
    public void testThrowExceptionWithInvalidParams() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ERROR);
        when(validator.validateParams(anyString(), anyString(), anyString(), anyString())).thenReturn(ERROR);
        searchService.find(CATEGORY, PRODUCT, MIN_PRICE, MAX_PRICE);
    }

    @Test
    public void testPassDataToDao() {
        searchService.find(CATEGORY, PRODUCT, MIN_PRICE, MAX_PRICE);
        verify(productDao).find(CATEGORY, PRODUCT, Double.parseDouble(MIN_PRICE), Double.parseDouble(MAX_PRICE));
        verifyNoMoreInteractions(productDao);
    }

    @Test
    public void testPassDataToDaoWithNullStrings() {
        searchService.find(null, null, MIN_PRICE, MAX_PRICE);
        verify(productDao).find(null, null, Double.parseDouble(MIN_PRICE), Double.parseDouble(MAX_PRICE));
        verifyNoMoreInteractions(productDao);
    }

    @Test
    public void testPassDataToDaoWithEmptyStrings() {
        searchService.find("", "", MIN_PRICE, MAX_PRICE);
        verify(productDao).find(null, null, Double.parseDouble(MIN_PRICE), Double.parseDouble(MAX_PRICE));
        verifyNoMoreInteractions(productDao);
    }

    @Test
    public void testPassDataToDaoWithNullPrices() {
        searchService.find(CATEGORY, PRODUCT, null, null);
        verify(productDao).find(CATEGORY, PRODUCT, null, null);
        verifyNoMoreInteractions(productDao);
    }

    @Configuration
    public static class LocalConfig {

        @Bean
        @Primary
        public Validator validator() {
            return mock(Validator.class);
        }
    }
}
