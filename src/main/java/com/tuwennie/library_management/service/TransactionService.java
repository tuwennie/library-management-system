package com.tuwennie.library_management.service;

import com.tuwennie.library_management.entity.Book;
import com.tuwennie.library_management.entity.Transaction;
import com.tuwennie.library_management.entity.User;
import com.tuwennie.library_management.repository.BookRepository;
import com.tuwennie.library_management.repository.TransactionRepository;
import com.tuwennie.library_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.tuwennie.library_management.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // KİTAP ÖDÜNÇ ALMA
    public String borrowBook(Long userId, Long bookId) {
        // 1. Kullanıcıyı bul
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı! ID: " + userId));

        // 2. Kitabı bul
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı! ID: " + bookId));

        // 3. Kitap zaten başkasında mı?
        if (transactionRepository.existsByBookAndReturnDateIsNull(book)) {
            throw new IllegalStateException("Bu kitap şu an başkasında, ödünç alamazsınız.");
        }

        // 4. Her şey temiz, işlemi başlat
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setBorrowDate(LocalDateTime.now()); // Şu anki zaman

        transactionRepository.save(transaction);
        return "Başarılı: Kitap ödünç alındı!";
    }

    // KİTAP İADE ETME
    public String returnBook(Long userId, Long bookId) {
        // 1. Kullanıcı ve Kitabı bul
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı! ID: " + userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı! ID: " + bookId));

        // 2. Bu kullanıcının bu kitapla ilgili açık bir işlemi var mı?
        Transaction transaction = transactionRepository.findByUserAndBookAndReturnDateIsNull(user, book)
                .orElseThrow(() -> new ResourceNotFoundException("Bu kullanıcıda iade edilecek böyle bir kitap yok!"));

        // 3. İade tarihini bas ve kaydet (İşlemi kapat)
        transaction.setReturnDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        return "Başarılı: Kitap iade edildi!";
    }
}