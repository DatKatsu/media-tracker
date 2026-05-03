package com.github.datkatsu.mediatracker.exception;

import com.github.datkatsu.mediatracker.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


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

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoResourceFound(NoResourceFoundException ex)
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

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    public ResponseEntity<ErrorResponseDto> handleTooManyRequests(HttpClientErrorException.TooManyRequests ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message", "Rate limited by MyAimeList API"));
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message",  ex.getMessage()));
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingRequestParameter(MissingServletRequestParameterException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message",  ex.getMessage()));
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponseDto> handleExternalApiError(ExternalApiException ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message",  ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorDto);
    }

    //Fallback Exception in case no other handler was found.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericError(Exception ex)
    {
        ErrorResponseDto errorDto = new ErrorResponseDto(Map.of("message",  "An unexpected error occurred"));
        log.error("Unhandled exception", ex);
        return ResponseEntity.internalServerError().body(errorDto);
    }

}
