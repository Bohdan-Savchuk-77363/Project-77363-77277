package com.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.web.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService {
    public List<Book> fetchBooks(String query, int maxResults){
        List<Book> books = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=" + maxResults;
            String json = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = new mapper.readTree(json);
            JsonNode item = root.get("items");

            if (items != null) {
                for (JsonNode item : items) {
                    JsonNode volumeInfo = item.get("volumeInfo");

                    String title = volumeInfo.has("title") ?
                            volumeInfo.get("title").asText() : "Unknown";
                    String author = volumeInfo.has("authors") ?
                            volumeInfo.get("authors").get(0).asText() : "Unknown";
                    double rating = volumeInfo.has(
                            "averageRating") ? volumeInfo.get("averageRating").asDouble() : 0.0;
                    int reviews = volumeInfo.has("ratingsCount") ?
                            volumeInfo.get("ratingsCount").asInt() : 0;
                    String description = volumeInfo.has(
                            "description") ? volumeInfo.get("description").asText() : "No description available";

                    String img = "";
                    if (volumeInfo.has("imageLinks") &&
                            volumeInfo.get("imageLinks").has("thumbnail")) {
                        img = volumeInfo.get("imageLinks").get(
                                "thumbnail").asText();
                        img = img.replace("http://", "https://");
                    }

                    books.add(new Book(title, author, rating,
                            reviews, img, description));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}