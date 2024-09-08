package model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "image_link")
    private String imageLink;

    // Constructors
    public Product() {}

    public Product(String title, String description, double price, String imageLink) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
    }

    // Getters and setters (same as before)
    // ...
}