package com.librarymanagement.system;

import com.librarymanagement.system.controller.BookController;
import com.librarymanagement.system.entity.Book;
import com.librarymanagement.system.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        bookController.getAllBooks();
        verify(bookService, times(1)).getAllBooks();
    }
}