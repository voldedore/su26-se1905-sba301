package vn.edu.fpt.sba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(ExampleArtistException.class)
    public ResponseEntity<ApiError> handlerException(ExampleArtistException exception) {
        ApiError json = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                "Not found"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerInvalidAPIRequestArg(
            MethodArgumentNotValidException ex
    ) {
        // Response body tra ve tu API
        ApiError json = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getFieldError().getDefaultMessage(),
                "Bad request"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
    }
}
