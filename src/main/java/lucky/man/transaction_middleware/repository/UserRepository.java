package lucky.man.transaction_middleware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lucky.man.transaction_middleware.entities.User;

import javax.swing.text.html.Option;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);

    }
