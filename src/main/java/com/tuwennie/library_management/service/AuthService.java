package com.tuwennie.library_management.service;

import com.tuwennie.library_management.dto.RegisterRequest;
import com.tuwennie.library_management.entity.Role;
import com.tuwennie.library_management.entity.User;
import com.tuwennie.library_management.repository.RoleRepository;
import com.tuwennie.library_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        // 1. Bu isimde kullanıcı var mı?
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Hata: Bu kullanıcı adı zaten alınmış!";
        }

        // 2. Yeni kullanıcı oluştur
        User user = new User();
        user.setUsername(request.getUsername());
        // Şifreyi DÜZ KAYDETMİYORUZ! Hashleyip (şifreleyip) kaydediyoruz.
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3. Rol Atama (Veritabanında ROLE_USER yoksa oluşturur)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        // 4. Kaydet
        userRepository.save(user);
        return "Kullanıcı başarıyla kaydedildi!";
    }
}