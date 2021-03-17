package io.github.tadeuszsikorski.gober.data.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.tadeuszsikorski.gober.data.entity.user.Role;
import io.github.tadeuszsikorski.gober.data.entity.user.Role.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    
    Role findByName(Roles name);
}
