package ru.kosdev.improve.dao;

import org.springframework.stereotype.Repository;
import ru.kosdev.improve.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kos on 05.09.2015.
 */
@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Category category) {
        entityManager.persist(category);
    }

}
