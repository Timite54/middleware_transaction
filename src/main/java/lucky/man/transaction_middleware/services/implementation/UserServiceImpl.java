package lucky.man.transaction_middleware.services.implementation;

import java.util.List;
import java.util.Optional;

import lucky.man.transaction_middleware.common.advice.exception.CustomAPIException;
import lucky.man.transaction_middleware.common.response.APIResponse;
import lucky.man.transaction_middleware.dto.UserRequestDto;
import lucky.man.transaction_middleware.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lucky.man.transaction_middleware.entities.User;
import lucky.man.transaction_middleware.repository.UserRepository;
import lucky.man.transaction_middleware.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public APIResponse<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        User user = new User();
        updateUser(user, userRequestDto);
        userRepository.save(user);
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return APIResponse.<UserResponseDto>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Enregistrment éffectué avec succès")
                .data(userResponseDto)
                .build();


    }

    @Override
    public List<User> loadUser() {
//        return userRepository.findAllById(id);
        return userRepository.findAll();

    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public  void updateUser(User user, UserRequestDto userRequestDto) {
//        user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow( () ->{
//            return new CustomAPIException(HttpStatus.NOT_FOUND, "User  not wit id %s not found".formatted(userRequestDto.getEmail()));
//        });
        user.setUserName(userRequestDto.getUserName());
        user.setEmail(userRequestDto.getEmail());

    }
}
