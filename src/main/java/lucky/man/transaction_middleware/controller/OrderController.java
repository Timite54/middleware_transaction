package lucky.man.transaction_middleware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lucky.man.transaction_middleware.common.response.APIResponse;
import lucky.man.transaction_middleware.dto.OrderRequestDto;
import lucky.man.transaction_middleware.dto.OrderResponseDto;
import lucky.man.transaction_middleware.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<APIResponse<OrderResponseDto>> CreateOrders(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        APIResponse<OrderResponseDto> response = orderService.creatOrder(orderRequestDto);
        return ResponseEntity.status(201).body(response);
    }
}