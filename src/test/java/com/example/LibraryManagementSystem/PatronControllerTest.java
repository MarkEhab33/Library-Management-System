package com.example.LibraryManagementSystem;


import com.example.LibraryManagementSystem.controller.PatronController;
import com.example.LibraryManagementSystem.entity.Patron;
import com.example.LibraryManagementSystem.service.PatronService;
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
public class PatronControllerTest {

    @InjectMocks
    private PatronController patronController;

    @Mock
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() {
        Patron patron1 = new Patron();
        Patron patron2 = new Patron();
        when(patronService.getAllPatrons()).thenReturn(Arrays.asList(patron1, patron2));

        ResponseEntity<List<Patron>> response = (ResponseEntity<List<Patron>>) patronController.getAllPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(patron1, patron2), response.getBody());
    }

    @Test
    public void testGetPatronById() {
        Patron patron = new Patron();
        when(patronService.getPatronById(1L)).thenReturn(Optional.of(patron));

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testAddPatron() {
        Patron patron = new Patron();
        when(patronService.addPatron(patron)).thenReturn(patron);

        Patron response = patronController.addPatron(patron);

        assertEquals(patron, response);
    }

    @Test
    public void testUpdatePatron() {
        Patron updatedPatron = new Patron();
        when(patronService.updatePatron(1L, updatedPatron)).thenReturn(updatedPatron);

        ResponseEntity<Patron> response = patronController.updatePatron(1L, updatedPatron);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }

    @Test
    public void testDeletePatron() {
        patronController.deletePatron(1L);

        // Verifying that no exception is thrown
    }
}

