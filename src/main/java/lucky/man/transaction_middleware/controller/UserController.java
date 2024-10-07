package lucky.man.transaction_middleware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lucky.man.transaction_middleware.entities.User;
import lucky.man.transaction_middleware.services.UserService;
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
    public void createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
    }

}
