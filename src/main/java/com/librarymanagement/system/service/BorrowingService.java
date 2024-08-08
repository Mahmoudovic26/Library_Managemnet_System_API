package com.librarymanagement.system.service;

import com.librarymanagement.system.entity.Book;
import com.librarymanagement.system.entity.BorrowingRecord;
import com.librarymanagement.system.entity.Patron;
import com.librarymanagement.system.exception.ResourceNotFoundException;
import com.librarymanagement.system.repository.BookRepository;
import com.librarymanagement.system.repository.BorrowingRecordRepository;
import com.librarymanagement.system.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        validateBookAndPatronIds(bookId, patronId);

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + patronId));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        return borrowingRecordRepository.save(borrowingRecord);
    }
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        validateBookAndPatronIds(bookId, patronId);

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found for book with id: " + bookId + " and patron with id: " + patronId));

        borrowingRecord.setReturnDate(LocalDate.now());

        return borrowingRecordRepository.save(borrowingRecord);
    }

    private void validateBookAndPatronIds(Long bookId, Long patronId) {
        if (bookId == null || bookId <= 0) {
            throw new IllegalArgumentException("Book ID cannot be null or negative.");
        }

        if (patronId == null || patronId <= 0) {
            throw new IllegalArgumentException("Patron ID cannot be null or negative.");
        }
    }
}