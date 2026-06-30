package com.web.entity;

public class Book {
    private String title;
    private String author;
    private Double rating;
    private Integer reviews;
    private String img;
    private String description;
    private String googleBooksId;
    private Integer pageCount;
    private String publisher;
    private String publishedDate;
    private String categories;
    private String language;
    private String previewLink;

    public Book() {}

    public Book(String title, String author, Double rating, Integer reviews, String img, String description, String googleBooksId) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.reviews = reviews;
        this.img = img;
        this.description = description;
        this.googleBooksId = googleBooksId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getReviews() { return reviews; }
    public void setReviews(Integer reviews) { this.reviews = reviews; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGoogleBooksId() { return googleBooksId; }
    public void setGoogleBooksId(String googleBooksId) { this.googleBooksId = googleBooksId; }

    public Integer getPageCount() { return pageCount; }
    public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getPreviewLink() { return previewLink; }
    public void setPreviewLink(String previewLink) { this.previewLink = previewLink; }
}