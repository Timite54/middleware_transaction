package lucky.man.transaction_middleware.services;

import java.util.List;

import lucky.man.transaction_middleware.common.response.APIResponse;
import lucky.man.transaction_middleware.dto.UserRequestDto;
import lucky.man.transaction_middleware.dto.UserResponseDto;
import lucky.man.transaction_middleware.entities.User;

public interface UserService {
    APIResponse<UserResponseDto>createUser(UserRequestDto userRequestDto);
    List<User> loadUser();
    void updateUser(Long id, User user);
    void deleteUser(Long id);


 

}
