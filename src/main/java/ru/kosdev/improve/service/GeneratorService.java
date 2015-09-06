package ru.kosdev.improve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosdev.improve.dao.CategoryDao;
import ru.kosdev.improve.dao.Cleaner;
import ru.kosdev.improve.dao.ProductDao;
import ru.kosdev.improve.entity.Category;
import ru.kosdev.improve.entity.Product;

/**
 * Created by Kos on 05.09.2015.
 */
@Service
public class GeneratorService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private Cleaner cleaner;

    @Transactional
    public void generateEntities() {
        cleaner.clean();

        Category phones = new Category("Phones");
        Category cars = new Category("Cars");
        Category clothes = new Category("Clothes");
        Category devices = new Category("Computer devices");
        Category balls = new Category("Balls");
        Category souvenir = new Category("Сувениры");

        categoryDao.save(phones);
        categoryDao.save(cars);
        categoryDao.save(clothes);
        categoryDao.save(devices);
        categoryDao.save(balls);
        categoryDao.save(souvenir);

        productDao.save(new Product(phones, "IPhone 6", 45000.54));
        productDao.save(new Product(phones, "IPhone 5", 40000.99));
        productDao.save(new Product(phones, "IPhone 4", 35000.50));
        productDao.save(new Product(phones, "Motorola C 650", 1500.20));
        productDao.save(new Product(phones, "Alcatel 310", 500.20));

        productDao.save(new Product(cars, "Toyota Land Cruiser", 2000000d));
        productDao.save(new Product(cars, "Lada Granta", 453000.25));
        productDao.save(new Product(cars, "Small car-toy", 25.70));
        productDao.save(new Product(cars, "Bus", 4500000d));

        productDao.save(new Product(clothes, "T-shirt", 25.70));

        productDao.save(new Product(devices, "Samsung Display 22", 7400.44));
        productDao.save(new Product(devices, "Benq Display 17", 4400.44));
        productDao.save(new Product(devices, "USB flash memory", 400.00));
        productDao.save(new Product(devices, "Motherboard", 10500.44));

        productDao.save(new Product(souvenir, "Матрёшка", 99.99));
        productDao.save(new Product(souvenir, "Магнит с кремлем", 199.99));
        productDao.save(new Product(souvenir, "Футболка с НГУ", 200.00));
        productDao.save(new Product(souvenir, "Флаг РФ", 150.00));
    }
}
