package lucky.man.transaction_middleware.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"status", "success", "message", "data"})
public class APIResponse<T> {
    private Integer status;
    private Boolean success;
    private String message;
    private T data;

    public  static <T> APIResponse<T> buildResponse(T data, HttpStatus status, String message) {
        return APIResponse.<T>builder()
                .status(status.value())
                .success(status.is2xxSuccessful())
                .message(message)
                .data(data)
                .build();

    }
}
