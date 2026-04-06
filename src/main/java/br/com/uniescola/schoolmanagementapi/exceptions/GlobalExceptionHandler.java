package br.com.uniescola.schoolmanagementapi.exceptions;

import br.com.uniescola.schoolmanagementapi.exceptions.ResourceNotFoundException;
import br.com.uniescola.schoolmanagementapi.infra.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura erros de "Não Encontrado" (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // 2. Captura erros genéricos (500) para não travar a API
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> standardError(Exception e, HttpServletRequest request) {
        StandardError err = new StandardError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
