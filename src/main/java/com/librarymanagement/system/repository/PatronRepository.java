package com.librarymanagement.system.repository;

import com.librarymanagement.system.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}