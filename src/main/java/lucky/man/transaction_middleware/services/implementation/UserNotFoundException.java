package lucky.man.transaction_middleware.services.implementation;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
