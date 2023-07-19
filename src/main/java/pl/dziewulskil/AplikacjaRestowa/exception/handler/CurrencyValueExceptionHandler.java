package pl.dziewulskil.AplikacjaRestowa.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dziewulskil.AplikacjaRestowa.controllers.CurrencyValueController;
import pl.dziewulskil.AplikacjaRestowa.exception.RestApplicationException;

@RestControllerAdvice(assignableTypes = CurrencyValueController.class)
public class CurrencyValueExceptionHandler {

    @ExceptionHandler(RestApplicationException.class)
    public ResponseEntity<String> handleException(RestApplicationException exception) {
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }
}
