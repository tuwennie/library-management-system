package com.tuwennie.library_management.service;

import com.tuwennie.library_management.entity.Book;
import com.tuwennie.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
@RequiredArgsConstructor // Repository'i otomatik enjekte eder (Constructor Injection)
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> getAllBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }

    // Yeni kitap kaydet
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
    
    // Kitap Güncelleme
    public Book updateBook(Long id, Book newBookData) {
        // 1. Veritabanında bu ID ile bir kitap var mı?
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            // 2. Varsa, eski bilgileri yenileriyle değiştir
            existingBook.setTitle(newBookData.getTitle());
            existingBook.setAuthor(newBookData.getAuthor());
            existingBook.setIsbn(newBookData.getIsbn());
            // isActive alanını da güncelleyebilirsin istersen
            
            // 3. Güncellenmiş halini kaydet
            return bookRepository.save(existingBook);
        }
        
        // Kitap bulunamazsa null döner
        return null;
    }
    
    // Kitap Silme (Soft Delete)
public void deleteBook(Long id) {
    Book book = bookRepository.findById(id).orElse(null);
    if (book != null) {
        book.setIsActive(false); // Durumu pasife çek
        bookRepository.save(book); // Güncelle
    }
}
}
