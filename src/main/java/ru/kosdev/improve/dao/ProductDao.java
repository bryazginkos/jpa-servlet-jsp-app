package ru.kosdev.improve.dao;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.kosdev.improve.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kos on 05.09.2015.
 */
@Repository
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Product product) {
        entityManager.persist(product);
    }

    /**
     * Возвращает список продуктов по критериям (аргументам)
     * @param category первые символы категории без учета регистра
     * @param product первые символы продукта без учета регистра
     * @param minPrice минимальная допустимая цена
     * @param maxPrice максимально допустимая цена
     * @return
     */
    public List<Product> find(@Nullable String category,@Nullable String product,
                           @Nullable Double minPrice, @Nullable Double maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> from = cq.from(Product.class);
        cq.select(from);

        Collection<Predicate> predicateCollection = new ArrayList<>();

        if (product != null) {
            predicateCollection.add(cb.like(cb.upper(from.get("name")), product.toUpperCase() + "%"));
        }

        if (category != null) {
            predicateCollection.add(cb.like(cb.upper(from.get("category").get("name")), category.toUpperCase() + "%"));
        }

        if (minPrice != null) {
            predicateCollection.add(cb.ge(from.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicateCollection.add(cb.le(from.get("price"), maxPrice));
        }
        cq.where(predicateCollection.toArray(new Predicate[predicateCollection.size()]));
        TypedQuery<Product> q = entityManager.createQuery(cq);
        return q.getResultList();
    }
}
