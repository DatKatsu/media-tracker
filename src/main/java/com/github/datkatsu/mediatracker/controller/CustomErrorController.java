package com.github.datkatsu.mediatracker.controller;

import com.github.datkatsu.mediatracker.dto.ErrorResponseDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Fallback error handler for errors that bypass @ControllerAdvice,
 * such as 404s for unknown routes or errors occurring outside the
 * dispatcher servlet. Replaces Spring Boot's default Whitelabel HTML
 * error page with a consistent JSON response.
 */

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponseDto> handleError(HttpServletRequest request) {
        System.out.println("Error Controller is called");
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        HttpStatus resolved = HttpStatus.resolve(statusCode);
        HttpStatus status =  resolved != null ? resolved : HttpStatus.INTERNAL_SERVER_ERROR;

        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        String detail = (message != null && !message.isBlank()) ? message : status.getReasonPhrase();


        return ResponseEntity.status(status).body(new ErrorResponseDto(Map.of("message", detail)));
    }
}