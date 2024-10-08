package lucky.man.transaction_middleware.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String userName;
    private String email;
}
