package com.librarymanagement.system.controller;

import com.librarymanagement.system.entity.Patron;
import com.librarymanagement.system.exception.ResourceNotFoundException;
import com.librarymanagement.system.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable @NotNull @Positive Long id) {
        try {
            Patron patron = patronService.getPatronById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found for this id :: " + id));
            return ResponseEntity.ok().body(patron);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addPatron(@Valid @RequestBody Patron patron) {
        try {
            Patron createdPatron = patronService.addPatron(patron);
            return ResponseEntity.ok(createdPatron);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatron(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody Patron patronDetails) {
        try {
            Patron updatedPatron = patronService.updatePatron(id, patronDetails);
            return ResponseEntity.ok(updatedPatron);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable @NotNull @Positive Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}