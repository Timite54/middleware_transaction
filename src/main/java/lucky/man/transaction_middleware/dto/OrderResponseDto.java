package lucky.man.transaction_middleware.dto;

import lombok.Data;
import lucky.man.transaction_middleware.entities.User;

@Data
public class OrderResponseDto {
    private String name;
    private Integer amount;
    private User user;

}
