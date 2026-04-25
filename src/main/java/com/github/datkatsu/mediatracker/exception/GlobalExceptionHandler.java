package com.github.datkatsu.mediatracker.exception;

import com.github.datkatsu.mediatracker.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponseDto errorDto = new ErrorResponseDto(errors);
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(MediaEntryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundErrors(MediaEntryNotFoundException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoHandlerFound(NoHandlerFoundException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidJson(HttpMessageNotReadableException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message", "Invalid value provided"));
        return ResponseEntity.badRequest().body(errorDto);
    }
}
