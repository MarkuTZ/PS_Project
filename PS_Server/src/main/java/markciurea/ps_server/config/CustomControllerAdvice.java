package markciurea.ps_server.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomControllerAdvice {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<CustomError> handleException(CustomError exc) {
        return new ResponseEntity<>(exc, exc.getCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeException> handleException(RuntimeException exc) {
        return new ResponseEntity<>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
