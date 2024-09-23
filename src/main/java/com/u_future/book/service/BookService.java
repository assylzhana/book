package com.u_future.book.service;

import com.github.dockerjava.api.exception.NotFoundException;
import com.u_future.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private long nextId = 1;

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book not found " + id));
    }

    public Book addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    public void deleteBook(Long id) {
        Book book = getBook(id);
        books.remove(book);
    }
    public void editBook(Book book) {
        Book existingBook = getBook(book.getId());

        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }

        if (book.getDescription() != null) {
            existingBook.setDescription(book.getDescription());
        }

        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }

        if (book.getPrice() != null) {
            existingBook.setPrice(book.getPrice());
        }
    }

}
