package com.example.demo.controller;
import com.example.demo.entity.BorrowingRecord;
import com.example.demo.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.returnBook(bookId);
        return ResponseEntity.ok(record);
    }
}
