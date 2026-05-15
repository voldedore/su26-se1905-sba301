package vn.edu.fpt.sba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
