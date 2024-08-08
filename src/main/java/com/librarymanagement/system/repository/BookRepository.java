package com.librarymanagement.system.repository;

import com.librarymanagement.system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}