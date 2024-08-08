package com.librarymanagement.system.controller;

import com.librarymanagement.system.entity.BorrowingRecord;
import com.librarymanagement.system.exception.ResourceNotFoundException;
import com.librarymanagement.system.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> borrowBook(
            @PathVariable @NotNull @Positive Long bookId,
            @PathVariable @NotNull @Positive Long patronId) {
        try {
            BorrowingRecord borrowingRecord = borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.status(HttpStatus.CREATED).body(borrowingRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> returnBook(
            @PathVariable @NotNull @Positive Long bookId,
            @PathVariable @NotNull @Positive Long patronId) {
        try {
            BorrowingRecord borrowingRecord = borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}