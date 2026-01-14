package com.tuwennie.library_management.repository;

import com.tuwennie.library_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // Rol ismine göre arama (Örn: "ROLE_ADMIN" verince rolü döner)
    Optional<Role> findByName(String name);
}
