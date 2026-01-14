package com.tuwennie.library_management.repository;

import com.tuwennie.library_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // 1. Login işlemi için kullanıcıyı bulur
    Optional<User> findByUsername(String username);

    // 2. Kayıt olurken "Bu isim daha önce alınmış mı?" kontrolü yapar
    Boolean existsByUsername(String username);
}