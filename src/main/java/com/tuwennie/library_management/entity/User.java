package com.tuwennie.library_management.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // 'user' SQL'de özel bir kelime olabilir, o yüzden 'users' yaptık
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Kullanıcının rolleri (Admin mi, User mı?)
    // FetchType.EAGER: Kullanıcıyı çektiğimiz an rollerini de peşinden getirir.
    // Security işlemleri için bu gereklidir.
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
