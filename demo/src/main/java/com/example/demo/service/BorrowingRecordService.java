package com.example.demo.service;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowingRecord;
import com.example.demo.entity.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }

    public BorrowingRecord returnBook(Long bookId) {
        BorrowingRecord record = borrowingRecordRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found"));
        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }
}

