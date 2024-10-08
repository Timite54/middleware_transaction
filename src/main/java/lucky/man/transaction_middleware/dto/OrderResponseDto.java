package lucky.man.transaction_middleware.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lucky.man.transaction_middleware.entities.User;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {
    private String name;
    private Integer amount;
    private UserResponseDto user;

}
