package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {}
