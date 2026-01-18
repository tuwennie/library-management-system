package com.tuwennie.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hangi Kullanıcı aldı?
    // ManyToOne: Bir kullanıcı birçok işlem yapabilir.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Hangi Kitabı aldı?
    // ManyToOne: Bir kitap tarih boyunca birçok kez ödünç alınabilir (farklı zamanlarda).
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Ne zaman aldı?
    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    // Ne zaman iade etti? (Henüz etmediyse null olabilir)
    @Column(name = "return_date")
    private LocalDateTime returnDate;
}