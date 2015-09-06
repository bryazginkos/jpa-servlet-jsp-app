package ru.kosdev.improve.entity;

import javax.persistence.*;

/**
 * Created by Kos on 04.09.2015.
 */
@Entity
@Table(name = Category.TABLE_NAME)
public class Category {

    public static final String TABLE_NAME = "CAT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 255)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
