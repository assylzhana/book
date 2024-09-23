package com.u_future.book.controller;

import com.github.dockerjava.api.exception.NotFoundException;
import com.u_future.book.model.Book;
import com.u_future.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@Tag(name="Book methods",  description = "Operations related to books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    //http://localhost:8080/swagger-ui/index.html#/
    private final BookService bookService;

    @Operation(summary = "Get all books")
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get book by id")
    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        try {
            Book book = bookService.getBook(id);
            return ResponseEntity.ok(book);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Add book")
    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.addBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit book by id")
    @PutMapping("/{id}")
    public ResponseEntity<?> editBook(@PathVariable Long id, @RequestBody Book newBook) {
        try {
            newBook.setId(id);
            bookService.editBook(newBook);
            return ResponseEntity.ok(newBook);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete book by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
