package lucky.man.transaction_middleware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lucky.man.transaction_middleware.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAllById(Long id);
    }
