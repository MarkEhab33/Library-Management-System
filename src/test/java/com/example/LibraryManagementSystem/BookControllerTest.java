package com.example.LibraryManagementSystem;


import com.example.LibraryManagementSystem.controller.BookController;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        Book book2 = new Book();
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(book1, book2), response.getBody());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        when(bookService.addBook(book)).thenReturn(book);

        Book response = bookController.addBook(book);

        assertEquals(book, response);
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = new Book();
        when(bookService.updateBook(1L, updatedBook)).thenReturn(updatedBook);

        ResponseEntity<Book> response = bookController.updateBook(1L, updatedBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
    public void testDeleteBook() {
        bookController.deleteBook(1L);

        // Verifying that no exception is thrown
    }
}

