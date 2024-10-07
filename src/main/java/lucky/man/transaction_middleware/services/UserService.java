package lucky.man.transaction_middleware.services;

import java.util.List;

import lucky.man.transaction_middleware.entities.User;

public interface UserService {
    User createUser(User user);
    List<User> loadUser(Long id);
    void updateUser(Long id, User user);
    void deleteUser(Long id);


 

}
