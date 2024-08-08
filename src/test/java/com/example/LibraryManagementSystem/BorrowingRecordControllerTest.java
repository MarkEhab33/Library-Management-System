package com.example.LibraryManagementSystem;

import com.example.LibraryManagementSystem.controller.BorrowingRecordController;
import com.example.LibraryManagementSystem.entity.BorrowingRecord;
import com.example.LibraryManagementSystem.service.BorrowingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class BorrowingRecordControllerTest {

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @Test
    public void testBorrowBook() {
        BorrowingRecord record = new BorrowingRecord();
        when(borrowingRecordService.borrowBook(1L, 1L)).thenReturn(record);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(record, response.getBody());
    }

    @Test
    public void testReturnBook() {
        BorrowingRecord record = new BorrowingRecord();
        when(borrowingRecordService.returnBook(1L)).thenReturn(record);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(record, response.getBody());
    }
}
