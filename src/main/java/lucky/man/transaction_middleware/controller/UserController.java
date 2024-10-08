package lucky.man.transaction_middleware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lucky.man.transaction_middleware.common.response.APIResponse;
import lucky.man.transaction_middleware.dto.UserRequestDto;
import lucky.man.transaction_middleware.dto.UserResponseDto;
import lucky.man.transaction_middleware.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<APIResponse<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        APIResponse<UserResponseDto> response = userService.createUser(userRequestDto);
        return ResponseEntity.status(201).body(response);
    }

}
