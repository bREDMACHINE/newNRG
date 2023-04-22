package get.newNRG.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("get.a.big.head.newNRG")
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleBadRequestException(final BadRequestException e) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", e.getMessage());
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNotFoundException(final NotFoundException e) {
        return new ResponseEntity<>(Map.of("NotFound Error", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleJwtAuthenticationException(final JwtAuthenticationException e) {
        return new ResponseEntity<>(Map.of("Authentication Error", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
