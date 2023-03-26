package com.vn.bookmanager.model;

public class Book {
    private Integer id;
    private String bookName;
    private String description;
    private double price;
    private String image;
    private int catId;

    public Book() {
    }

    public Book(String bookName,int catId, String description, int id, String image,double price) {
        this.id =id;
        this.bookName = bookName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.catId = catId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", catId=" + catId +
                '}';
    }
}
