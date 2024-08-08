package com.librarymanagement.system.service;

import com.librarymanagement.system.entity.Patron;
import com.librarymanagement.system.exception.ResourceNotFoundException;
import com.librarymanagement.system.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }
    @Transactional
    public Patron addPatron(Patron patron) {
        validatePatronDetails(patron);
        return patronRepository.save(patron);
    }
    @Transactional
    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found for this id :: " + id));
        validatePatronDetails(patronDetails);
        patron.setName(patronDetails.getName());
        patron.setContactInformation(patronDetails.getContactInformation());
        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found for this id :: " + id));
        patronRepository.delete(patron);
    }

    private void validatePatronDetails(Patron patron) {
        if (patron.getName() == null || patron.getContactInformation() == null) {
            throw new IllegalArgumentException("Patron name and contact information cannot be null.");
        }
    }
}