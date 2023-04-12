package com.vn.bookmanager.MODEL;

public class Book {
    String bookName,description,image;
    int id, catId;
    double price;

    public Book(String bookName,int catId, String description,int id, String image, double price) {
        this.bookName = bookName;
        this.description = description;
        this.image = image;
        this.id = id;
        this.catId = catId;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
