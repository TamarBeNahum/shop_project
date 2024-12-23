package com.proj.shop.product;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, name = "stock_quantity")
    private int stock_quantity;

    private String image_path;

    ///GETTERS///
    public int getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Double getPrice() {return price;}
    public int getStock_quantity() {return stock_quantity;}
    public String getImage_path() {return image_path;}

    ///SETTERS///
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(Double price) {this.price = price;}
    public void setStock_quantity(int stock_quantity) {this.stock_quantity = stock_quantity;}
    public void setImage_path(String image_path) {this.image_path = image_path;}
}
