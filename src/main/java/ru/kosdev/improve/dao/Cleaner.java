package ru.kosdev.improve.dao;

import org.springframework.stereotype.Service;
import ru.kosdev.improve.entity.Category;
import ru.kosdev.improve.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kos on 06.09.2015.
 */
@Service
public class Cleaner {

    @PersistenceContext
    private EntityManager entityManager;

    public void clean() {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE  " + Product.TABLE_NAME).executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE  " + Category.TABLE_NAME).executeUpdate();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
