package vn.edu.fpt.sba.authserver.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.authserver.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);
}
