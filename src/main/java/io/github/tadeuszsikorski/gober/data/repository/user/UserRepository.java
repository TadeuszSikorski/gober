package io.github.tadeuszsikorski.gober.data.repository.user;

import java.util.UUID;

import io.github.tadeuszsikorski.gober.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    
    User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
    
}
