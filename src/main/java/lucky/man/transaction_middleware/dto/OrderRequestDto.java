package lucky.man.transaction_middleware.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotBlank(message = "Le nom obligatoire")
    private String name;
    private Integer amount;
    private Long userId;

}
