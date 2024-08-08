package com.example.demo.repository;

import com.example.demo.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {}
