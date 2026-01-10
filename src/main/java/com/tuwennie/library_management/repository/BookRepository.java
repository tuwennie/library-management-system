package com.tuwennie.library_management.repository;

import com.tuwennie.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Yazar ismine göre arama
    List<Book> findByAuthor(String author);
    // isActive alanı "true" olanları getir
    List<Book> findByIsActiveTrue();
}