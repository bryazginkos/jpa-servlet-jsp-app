package ru.kosdev.improve.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kosdev.improve.dao.ProductDao;
import ru.kosdev.improve.entity.Product;

import java.util.List;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.isNullOrEmpty;


/**
 * Created by Kos on 04.09.2015.
 */
@Service
public class SearchService {

    @Autowired
    private Validator validator;

    @Autowired
    private ProductDao productDao;

    /**
     * Поиск продукта по параметрам
     * @param category Первые символы категории
     * @param product Первые символы продукта
     * @param minPrice Минимальная цена
     * @param maxPrice Максимальная цена
     * @return
     * @throws IllegalArgumentException
     */
    public List<Product> find(@Nullable String category, @Nullable String product,
                           @Nullable String minPrice, @Nullable String maxPrice) throws IllegalArgumentException {
        String error = validator.validateParams(category, product, minPrice, maxPrice);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }

        Double min = !isNullOrEmpty(minPrice) ? Double.parseDouble(minPrice) : null;
        Double max = !isNullOrEmpty(maxPrice) ? Double.parseDouble(maxPrice) : null;
        return productDao.find(emptyToNull(category), emptyToNull(product), min, max);
    }



}
