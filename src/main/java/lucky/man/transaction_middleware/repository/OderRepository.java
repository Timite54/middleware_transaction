package lucky.man.transaction_middleware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lucky.man.transaction_middleware.entities.Orders;

public interface OderRepository extends JpaRepository<Orders, Long>{

    
} 