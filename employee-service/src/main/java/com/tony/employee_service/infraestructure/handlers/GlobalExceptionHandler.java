package com.tony.employee_service.infraestructure.handlers;

import com.tony.employee_service.domain.exceptions.EmployeeException;
import com.tony.employee_service.domain.exceptions.NotFoundException;
import com.tony.employee_service.infraestructure.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(
            NotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO error =
                new ErrorResponseDTO(ex.getMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmployeeException(
            NotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO error =
                new ErrorResponseDTO(ex.getMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorResponseDTO error =
                new ErrorResponseDTO(ex.getMessage(), request.getRequestURL().toString());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            HttpServletRequest request, Exception ex) {
        ErrorResponseDTO error =
                new ErrorResponseDTO("Error interno en el servidor", request.getRequestURL().toString());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
