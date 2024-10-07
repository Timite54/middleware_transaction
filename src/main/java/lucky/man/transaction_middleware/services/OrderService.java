package lucky.man.transaction_middleware.services;

import lucky.man.transaction_middleware.dto.OrderRequestDto;
import lucky.man.transaction_middleware.dto.OrderResponseDto;
import lucky.man.transaction_middleware.entities.Orders;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<OrderResponseDto> creatOrder(OrderRequestDto orderRequestDto);
    Orders loadOrder(Long id);
    void updateUser(Long id, Orders order);
    void deleteUser(Long id);
}






