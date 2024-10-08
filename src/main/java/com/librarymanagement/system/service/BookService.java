package com.librarymanagement.system.service;

import com.librarymanagement.system.entity.Book;
import com.librarymanagement.system.exception.ResourceNotFoundException;
import com.librarymanagement.system.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    @Transactional
    public Book addBook(Book book) {
        validateBookDetails(book);
        return bookRepository.save(book);
    }
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
        validateBookDetails(bookDetails);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsbn(bookDetails.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
        bookRepository.delete(book);
    }

    private void validateBookDetails(Book book) {
        if (book.getTitle() == null|| Objects.equals(book.getTitle(), "") || book.getAuthor() == null || Objects.equals(book.getAuthor(), "") || book.getPublicationYear() == 0 || book.getIsbn() == null|| Objects.equals(book.getIsbn(), "")) {
            throw new IllegalArgumentException("Book title, author, publication year, and ISBN cannot be null.");
        }
    }
}