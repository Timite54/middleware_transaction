package lucky.man.transaction_middleware.services.implementation;

import java.util.List;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> loadUser(Long id) {
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

}
