package ru.kosdev.improve.entity;

import javax.persistence.*;

/**
 * Created by Kos on 04.09.2015.
 */
@Entity
@Table(name = Product.TABLE_NAME)
public class Product {

    public static final String TABLE_NAME = "PROD";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAT_ID")
    private Category category;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE", precision = 6, scale = 2)
    private Double price;

    public Product() {
    }

    public Product(Category category, String name, Double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
