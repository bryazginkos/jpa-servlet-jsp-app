package ru.kosdev.improve.service;

import com.google.common.base.Strings;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by Kos on 05.09.2015.
 */
@Service
public class Validator {

    private static String LETTER_DIGITS = "^[а-яА-ЯёЁa-zA-Z0-9\\s\\-]+$";

    private static String INVALID_MESSAGE = "%s is not valid";

    /**
     * Валидирует параметры для поиска. Для успешной валидации нужен хотя бы один не пустой валидный параметр.
     * В случае успешной валидации возвращает null, иначе сообщение об ошибке
     * В случае нескольких ошибок возвращает первую найденную ошибку
     * @param category Первые символы категории (русские, английские буквы и цифры)
     * @param product Первые символы продукта (русские, английские буквы и цифры)
     * @param minPrice минимальная цена (конвертируется в double)
     * @param maxPrice максимальная цена (конвертируется в double)
     * @return
     */
    @Nullable
    public String validateParams(@Nullable String category, @Nullable String product,
                                 @Nullable String minPrice, @Nullable String maxPrice) {
        String error;
        error = checkString(category, "Category");
        if (error != null) return error;

        error = checkString(product, "Product");
        if (error != null) return error;

        error = checkDoubleString(minPrice, "Min price");
        if (error != null) return error;

        error = checkDoubleString(maxPrice, "Max price");
        if (error != null) return error;

        if (allIsEmptyOrNull(category, product, minPrice, maxPrice)) {
            return "At least one criteria is needed";
        }
        return null;
    }

    private String checkString(String str, String field) {
        if (!isNullOrEmpty(str) && !str.matches(LETTER_DIGITS)) {
            return String.format(INVALID_MESSAGE, field);
        }
        return null;
    }

    private String checkDoubleString(String str, String field) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return String.format(INVALID_MESSAGE, field);
        }
        return null;
    }

    private boolean allIsEmptyOrNull(String... strings) {
        if (strings == null) {
            return true;
        }
        for (String string : strings) {
            if (!Strings.isNullOrEmpty(string)) return false;
        }
        return true;
    }
}
