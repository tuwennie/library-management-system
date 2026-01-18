package com.tuwennie.library_management.controller;

import com.tuwennie.library_management.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Ödünç Alma: POST http://localhost:8080/api/transactions/borrow?userId=1&bookId=1
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return transactionService.borrowBook(userId, bookId);
    }

    // İade Etme: POST http://localhost:8080/api/transactions/return?userId=1&bookId=1
    @PostMapping("/return")
    public String returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return transactionService.returnBook(userId, bookId);
    }
}