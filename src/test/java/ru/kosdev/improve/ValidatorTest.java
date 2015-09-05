package ru.kosdev.improve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.kosdev.improve.config.TestConfig;
import ru.kosdev.improve.service.Validator;

import static org.junit.Assert.*;

/**
 * Created by Kos on 05.09.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ValidatorTest {

    private static final String CATEGORY = "category";
    private static final String PRODUCT = "product";
    private static final String MIN_PRICE = "1";
    private static final String MAX_PRICE = "20";
    private static final String RUSSIAN_TEXT = "Русский текст";

    @Autowired
    private Validator validator;

    @Test
    public void testAllNullInvalid() {
        assertNotNull(validator.validateParams(null, null, null, null));
    }

    @Test
    public void testAllEmptyInvalid() {
        assertNotNull(validator.validateParams("", "", "", ""));
    }

    @Test
    public void testNullAndEmptyInvalid() {
        assertNotNull(validator.validateParams(null, "", null, ""));
    }

    @Test
    public void testValidWithCategory() {
        assertNull(validator.validateParams(CATEGORY, "", "", ""));
    }

    @Test
    public void testValidWithProduct() {
        assertNull(validator.validateParams("", PRODUCT, "", ""));
    }

    @Test
    public void testValidWithMinPrice() {
        assertNull(validator.validateParams("", "", MIN_PRICE, ""));
    }

    @Test
    public void testValidWithMaxPrice() {
        assertNull(validator.validateParams("", "", "", MAX_PRICE));
    }

    @Test
    public void testValidWithAllParams() {
        assertNull(validator.validateParams(CATEGORY, PRODUCT, MIN_PRICE, MAX_PRICE));
    }

    @Test
    public void testValidWithMaxPriceNAN() {
        assertNotNull(validator.validateParams("", "", "", "NAN"));
    }

    @Test
    public void testValidWithMinPriceNAN() {
        assertNotNull(validator.validateParams("", "", "NAN", ""));
    }


    @Test
    public void testValidWithDigitsText() {
        assertNull(validator.validateParams(CATEGORY + MAX_PRICE, PRODUCT+MIN_PRICE, MIN_PRICE, MAX_PRICE));
    }

    @Test
    public void testValidWithRussianText() {
        assertNull(validator.validateParams(RUSSIAN_TEXT, RUSSIAN_TEXT, MIN_PRICE, MAX_PRICE));
    }

    @Test
    public void testValidWithDash() {
        assertNull(validator.validateParams(CATEGORY + "-" + CATEGORY, PRODUCT + " - " + PRODUCT, MIN_PRICE, MAX_PRICE));
    }

    @Test
    public void testValidWithSpace() {
        assertNull(validator.validateParams(CATEGORY + " " + CATEGORY, PRODUCT + " " + PRODUCT, MIN_PRICE, MAX_PRICE));
    }

    @Test
    public void testValidWithSpacesAndDash() {
        assertNull(validator.validateParams(CATEGORY + " - " + CATEGORY, PRODUCT + " - " + PRODUCT, MIN_PRICE, MAX_PRICE));
    }
}
