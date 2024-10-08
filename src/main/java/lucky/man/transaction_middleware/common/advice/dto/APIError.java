package lucky.man.transaction_middleware.common.advice.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError<T> {
    private Integer status;
    private String title;
    private final Boolean success = false;
    private T message;
    private String error;
}
