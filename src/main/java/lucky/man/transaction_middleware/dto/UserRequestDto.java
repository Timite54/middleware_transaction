package lucky.man.transaction_middleware.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long UserId;
    private String userName;
    private String email;
}
