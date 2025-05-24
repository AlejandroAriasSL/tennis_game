package dev.alejandro.tennis_game.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ApiError> handlePlayerNotFound(PlayerNotFoundException exception){
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(), 
            HttpStatus.NOT_FOUND.getReasonPhrase(), 
            exception.getMessage());
        
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
