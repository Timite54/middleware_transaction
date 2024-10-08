package lucky.man.transaction_middleware.common.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lucky.man.transaction_middleware.common.advice.dto.APIError;
import lucky.man.transaction_middleware.common.advice.exception.CustomAPIException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Gère les exceptions de format invalide.
     * Ces exceptions se produisent généralement lors de la désérialisation des données d'entrée.
     *
     * @param ex L'exception de format invalide
     * @return ResponseEntity contenant les détails de l'erreur
     */
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<APIError<Object>> handleInvalidFormatException(InvalidFormatException ex) {
        log.error("InvalidFormatException: {}", ex.getMessage());
        return createErrorResponse(ex.getMessage());
    }

    /**
     * Gère les exceptions de validation des arguments de méthode.
     * Cette méthode est appelée lorsque la validation des données d'entrée échoue.
     *
     * @param ex L'exception contenant les détails des erreurs de validation
     * @param headers Les en-têtes HTTP de la réponse
     * @param status Le code de statut HTTP
     * @param request La requête web en cours
     * @return ResponseEntity contenant une liste détaillée des erreurs de validation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> fieldError = new HashMap<>();
                    fieldError.put("fieldName", error.getField());
                    fieldError.put("message", error.getDefaultMessage());
                    return fieldError;
                })
                .collect(Collectors.toList());

        return createErrorResponseObject(errors, "Validation Error");
    }
    /**
     * Gère les exceptions de violation de contraintes.
     * Ces exceptions se produisent généralement lors de la validation des entités JPA.
     *
     * @param ex L'exception contenant les détails des violations de contraintes
     * @return ResponseEntity contenant une liste des messages d'erreur de validation
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return createErrorResponseObject(errors, "Constraint Violations");
    }

    /**
     * Crée une réponse d'erreur standardisée.
     * Cette méthode est utilisée pour créer une réponse d'erreur cohérente pour toutes les exceptions.
     *
     * @param message Le message d'erreur
     * @return ResponseEntity contenant les détails de l'erreur
     */
    private ResponseEntity<APIError<Object>>  createErrorResponse(String message) {
        APIError<Object> apiError = APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(Collections.singletonList(message))
                .error("Invalid Format")
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Crée une réponse d'erreur standardisée pour Object.
     * Cette méthode est similaire à createErrorResponse, mais permet de gérer des messages d'erreur multiples.
     *
     * @param message Le message d'erreur ou la liste des messages d'erreur
     * @param error   Le type d'erreur
     * @return ResponseEntity contenant les détails de l'erreur
     */
    private ResponseEntity<Object> createErrorResponseObject(Object message, String error) {
        APIError<Object> apiError = APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message instanceof List ? message : Collections.singletonList(message))
                .error(error)
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomAPIException.class)
    public ResponseEntity<APIError<Object>> handleCustomAPIException(CustomAPIException ex) {
        return ex.createErrorResponse();
    }

//        @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    Result handleAccessDeniedException(NoHandlerFoundException ex) {
//        return new Result(false, StatusCode.NOT_FOUND, "This API endpoint is not found.", ex.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError<Object>> handleException(Exception ex) {
        String errorMessage = "An error occurred. Please try again later."+ex.getMessage();

        APIError<Object> apiError = APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(Collections.singletonList(errorMessage))
                .error("Internal Server Error")
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
