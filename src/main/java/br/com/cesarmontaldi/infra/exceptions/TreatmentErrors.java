package br.com.cesarmontaldi.infra.exceptions;

import br.com.cesarmontaldi.model.ValidationExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TreatmentErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorsList::new).toList());
    }

    @ExceptionHandler(ValidationExceptions.class)
    public ResponseEntity errorRegrasDeNegocio(ValidationExceptions exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record ErrorsList(String campo, String message) {
        public ErrorsList(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
