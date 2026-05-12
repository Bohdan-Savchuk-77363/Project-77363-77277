package com.web.repository;

import com.web.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    public List<Book> booksInfo() {
        return List.of(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 4.5, 2341, "https://placehold.co/200x300/2C3038/F0F0F0?text=Book"),
                new Book("To Kill a Mockingbird", "Harper Lee", 5.0, 5120, "https://placehold.co/200x300/880000/ffffff?text=Book"),
                new Book("1984", "George Orwell", 4.0, 8764, "https://placehold.co/200x300/0077cc/ffffff?text=Book"),
                new Book("Pride and Prejudice", "Jane Austen", 4.5, 3210, "https://placehold.co/200x300/2d6a4f/ffffff?text=Book"),
                new Book("The Catcher in the Rye", "J.D. Salinger", 3.5, 1987, "https://placehold.co/200x300/7B2FF7/ffffff?text=Book"),
                new Book("Brave New World", "Aldous Huxley", 2.5, 4532, "https://placehold.co/200x300/e76f51/ffffff?text=Book"),
                new Book("The Hobbit", "J.R.R. Tolkien", 5.0, 9870, "https://placehold.co/200x300/457b9d/ffffff?text=Book"),
                new Book("Fahrenheit 451", "Ray Bradbury", 4.0, 3456, "https://placehold.co/200x300/e63946/ffffff?text=Book"),
                new Book("The Alchemist", "Paulo Coelho", 4.5, 7654, "https://placehold.co/200x300/f4a261/1a1a2e?text=Book"),
                new Book("Don Quixote", "Cervantes", 4.0, 2100, "https://placehold.co/200x300/264653/ffffff?text=Book"),
                new Book("Crime and Punishment", "F. Dostoevsky", 5.0, 6543, "https://placehold.co/200x300/6d6875/ffffff?text=Book"),
                new Book("The Little Prince", "A. de Saint-Exupéry", 5.0, 11230, "https://placehold.co/200x300/023e8a/ffffff?text=Book")
        );
    }
}
