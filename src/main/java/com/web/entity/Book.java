package com.web.entity;

public class Book {
    private String title;
    private String author;
    private Double rating;
    private Integer reviews;
    private String img;

    public Book(String title, String author, Double rating, Integer reviews, String img) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.reviews = reviews;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
