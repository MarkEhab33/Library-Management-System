
package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entity.Patron;
import com.example.LibraryManagementSystem.repository.PatronRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    @Cacheable(value = "patrons", key = "'all-patrons'")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable(value = "patrons", key = "#id")
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Transactional
    @CachePut(value = "patrons", key = "#patron.id")
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    @CachePut(value = "patrons", key = "#id")
    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
        patron.setName(patronDetails.getName());
        patron.setEmail(patronDetails.getEmail());
        patron.setPhoneNumber(patronDetails.getPhoneNumber());
        return patronRepository.save(patron);
    }

    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
