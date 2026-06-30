package com.web.service;

import com.web.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Service
public class GoogleBooksService {

    @Value("${google.books.api.key}")
    private String apiKey;

    @Value("${google.books.api.url}")
    private String apiUrl;

    @Value("${google.books.default.query}")
    private String defaultQuery;

    @Value("${google.books.default.maxResults}")
    private int defaultMaxResult;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Book> getDefaultBooks() {
        return fetchBooks(defaultQuery, defaultMaxResult);
    }

    public List<Book> fetchBooks(String query, int maxResults) {
//        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
//                .queryParam("q", query)
//                .queryParam("maxResults", maxResults)
//                .queryParam("key", apiKey)
//                .toUriString();

        String url = String.format("%s?q=%s&maxResults=%s&key=%s", apiUrl, query, maxResults, apiKey);
        List<Book> books = new ArrayList<>();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            if (items.isMissingNode() || !items.isArray()) return books;
            for (JsonNode item : items) {
                JsonNode volumeInfo = item.path("volumeInfo");
                String id = item.path("id").asText("");
                String title = volumeInfo.path("title").asText("Unknown Title");
                List<String> authors = new ArrayList<>();
                volumeInfo.path("authors").forEach(a -> authors.add(a.asText()));
                String author = authors.isEmpty() ? "Unknown Author" : String.join(", ", authors);
                
                double rating = volumeInfo.path("averageRating").asDouble(0.0);
                int reviews = volumeInfo.path("ratingsCount").asInt(0);

                String img = volumeInfo
                        .path("imageLinks")
                        .path("thumbnail")
                        .asText("https://placehold.co/200x300/2C3038/F0F0F0?text=No+Cover");
                img = img.replace("http://", "https://");

                String description = volumeInfo.path("description").asText("");
                books.add(new Book(title, author, rating, reviews, img, description, id));
            }
        } catch (Exception exception) {
            System.err.println("Google books API error: " + exception.getMessage());
        }
        return books;
    }

    public List<Book> searchBooks(String query) {
        return fetchBooks(query, defaultMaxResult);
    }

    public Map<String, Object> fetchBooksWithTotal(String query, int maxResults, int startIndex) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", query)
                .queryParam("maxResults", maxResults)
                .queryParam("startIndex", startIndex)
                .queryParam("key", apiKey)
                .toUriString();

        List<Book> books = new ArrayList<>();
        int totalItems = 0;

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            totalItems = root.path("totalItems").asInt(0);
            JsonNode items = root.path("items");

            if (items.isMissingNode() || !items.isArray()) {
                Map<String, Object> result = new HashMap<>();
                result.put("books", books);
                result.put("totalItems", totalItems);
                return result;
            }

            for (JsonNode item : items) {
                JsonNode volumeInfo = item.path("volumeInfo");
                String id = item.path("id").asText("");
                String title = volumeInfo.path("title").asText("Unknown Title");
                List<String> authors = new ArrayList<>();
                volumeInfo.path("authors").forEach(a -> authors.add(a.asText()));
                String author = authors.isEmpty() ? "Unknown Author" : String.join(", ", authors);

                double rating = volumeInfo.path("averageRating").asDouble(0.0);
                int reviews = volumeInfo.path("ratingsCount").asInt(0);

                String img = volumeInfo
                        .path("imageLinks")
                        .path("thumbnail")
                        .asText("https://placehold.co/200x300/2C3038/F0F0F0?text=No+Cover");
                img = img.replace("http://", "https://");

                String description = volumeInfo.path("description").asText("");
                books.add(new Book(title, author, rating, reviews, img, description, id));
            }
        } catch (Exception exception) {
            System.err.println("Google books API error: " + exception.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("books", books);
        result.put("totalItems", totalItems);
        return result;
    }
}