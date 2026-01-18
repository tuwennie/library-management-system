package com.tuwennie.library_management.repository;

import com.tuwennie.library_management.entity.Book;
import com.tuwennie.library_management.entity.Transaction;
import com.tuwennie.library_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 1. Bir kitap şu an ödünçte mi? (İade tarihi NULL olan bir kayıt var mı?)
    boolean existsByBookAndReturnDateIsNull(Book book);

    // 2. Bir kullanıcının iade etmeye çalıştığı kitabı bul (Henüz iade edilmemiş işlemi getir)
    Optional<Transaction> findByUserAndBookAndReturnDateIsNull(User user, Book book);
}