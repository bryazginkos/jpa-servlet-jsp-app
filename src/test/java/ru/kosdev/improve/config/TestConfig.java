package ru.kosdev.improve.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kosdev.improve.dao.CategoryDao;
import ru.kosdev.improve.dao.ProductDao;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Kos on 05.09.2015.
 */
@Configuration
@ComponentScan(basePackages = "ru.kosdev.improve.service")
public class TestConfig {
    @Bean
    public ProductDao productDao() {
        return Mockito.mock(ProductDao.class);
    }

    @Bean
    public CategoryDao categoryDao() {
        return Mockito.mock(CategoryDao.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }

}
