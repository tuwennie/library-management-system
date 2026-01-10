package com.tuwennie.library_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data // Lombok: Getter, Setter, toString metodlarını otomatik oluşturur
@NoArgsConstructor // Parametresiz constructor
@AllArgsConstructor // Tüm parametreleri alan constructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", unique = true)
    private String isbn;

    // Soft delete için (Kitabı gerçekten silmeyiz, bu alanı false yaparız)
    @Column(name = "is_active")
    private Boolean isActive = true; 
}