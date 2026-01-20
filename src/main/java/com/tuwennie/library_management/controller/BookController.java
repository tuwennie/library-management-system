package com.tuwennie.library_management.controller;

import com.tuwennie.library_management.entity.Book;
import com.tuwennie.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController // Bu sınıfın bir API dış kapısı olduğunu belirtir
@RequestMapping("/api/books") // Tüm istekler "http://localhost:8080/api/books" adresine gelecek
@RequiredArgsConstructor // Service'i otomatik bağlar
public class BookController {

    private final BookService bookService;

    // GET http://localhost:8080/api/books?page=0&size=5&sortBy=title
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return bookService.getAllBooks(page, size, sortBy);
    }

    // 2. Yeni kitap ekleyen uç (POST isteği)
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // @RequestBody: Gelen JSON verisini Book nesnesine çevirir
        return bookService.saveBook(book);
    }
    
    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
    // 4. Kitap Güncelle (PUT isteği)
    // Örnek: /api/books/1 (1 numaralı kitabı günceller)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book newBookData) {
        return bookService.updateBook(id, newBookData);
    }
    
    // 5. Kitap Sil (DELETE isteği)
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Kitap başarıyla silindi (Soft Delete uygulandı).";
    }
}