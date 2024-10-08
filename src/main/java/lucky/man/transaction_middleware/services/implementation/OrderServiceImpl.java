package lucky.man.transaction_middleware.services.implementation;

import lucky.man.transaction_middleware.common.advice.exception.CustomAPIException;
import lucky.man.transaction_middleware.common.response.APIResponse;
import lucky.man.transaction_middleware.dto.OrderResponseDto;
import lucky.man.transaction_middleware.entities.Orders;
import lucky.man.transaction_middleware.entities.User;
import lucky.man.transaction_middleware.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lucky.man.transaction_middleware.dto.OrderRequestDto;
import lucky.man.transaction_middleware.repository.OderRepository;
import lucky.man.transaction_middleware.services.OrderService;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse<OrderResponseDto> creatOrder(OrderRequestDto orderRequestDto) {
        Optional<User> existuser = userRepository.findById(orderRequestDto.getUserId());
        if(existuser.isEmpty()) {
            throw new CustomAPIException(HttpStatus.NOT_FOUND,"User " + orderRequestDto.getUserId() + " does not exist");
        }

        if(orderRequestDto.getAmount() > 500000) {
            throw new CustomAPIException(HttpStatus.CONFLICT,"Vous ne pouvez pas effectuer une telle commande en ligne, veillez vous rendre au store");
        }
        Orders orders = new Orders();
        updateOders(orders, orderRequestDto);
        orderRepository.save(orders);
        OrderResponseDto orderResponseDto = modelMapper.map(orders, OrderResponseDto.class);
        return APIResponse.<OrderResponseDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Enregistrment éffectué avec succès")
                .data(orderResponseDto)
                .build();
    }

    @Override
    public Orders loadOrder(Long id) {
                // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadOrder'");
    }

    @Override
    public void updateUser(Long id, Orders order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public void updateOders(Orders orders, OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(() ->
        {
            return new CustomAPIException(HttpStatus.NOT_FOUND, "User not found with id " + orderRequestDto.getUserId());
        });
        orders.setName(orderRequestDto.getName());
        orders.setAmount(orderRequestDto.getAmount());
        orders.setUser(user);


    }



}
