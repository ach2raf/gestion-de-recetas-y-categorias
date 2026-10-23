package com.salesianos.dam.gestion_de_recetas_y_categorias.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleProductNotFound(EntityNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Recurso no encontrado");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://example.com/errors/not-found"));

        return problem;
    }


    @ExceptionHandler(IngredienteAlreadyAddedException.class)
    public ProblemDetail ingredienteYaAnadido(IngredienteAlreadyAddedException ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problema.setTitle("Ingrediente ya añadido");
        return problema;
    }

    @ExceptionHandler(TimeInvalidException.class)
    public ProblemDetail tiempoInvalido(TimeInvalidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Tiempo de preparación inválido");
        return problemDetail;
    }




    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        List<ApiValidationSubError> subErrors =
                ex.getConstraintViolations().stream()
                        .map(ApiValidationSubError::from)
                        .toList();

        result.setProperty("invalid-params", subErrors);

        return result;

    }

    @Builder
    record ApiValidationSubError(
            String object,
            String message,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String field,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            Object rejectedValue
    ) {

        public ApiValidationSubError(String object, String message) {
            this(object, message, null, null);
        }

        public static ApiValidationSubError from(ObjectError error) {

            ApiValidationSubError result = null;

            if (error instanceof FieldError fieldError) {
                result = ApiValidationSubError.builder()
                        .object(fieldError.getObjectName())
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .build();
            } else {
                result = ApiValidationSubError.builder()
                        .object(error.getObjectName())
                        .message(error.getDefaultMessage())
                        .build();
            }

            return result;


        }

        public static ApiValidationSubError from(ConstraintViolation v) {
            return ApiValidationSubError.builder()
                    .message(v.getMessage())
                    .rejectedValue(v.getInvalidValue())
                    .object(v.getRootBeanClass().getSimpleName())
                    .field(getFieldName(v))
                    .build();
        }


        private static String getFieldName(ConstraintViolation<?> v) {

            String field = null;

            for (Path.Node node : v.getPropertyPath()) {
                if (node.getKind() == ElementKind.PROPERTY || node.getKind() == ElementKind.PARAMETER) {
                    field = node.getName() != null ? node.getName() : node.toString();
                }
            }
            return field;
        }
    }





}
