package guru.springframework.spring6restmvc.controller;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jt, Spring Framework Guru.
 */
@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler
    public ResponseEntity<?> handleJPAViolations(TransactionSystemException exception) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        Throwable cause = exception.getCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException ve = (ConstraintViolationException) cause;

            List<Map<String, String>> errors = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String, String> errMap = new HashMap<>();
                        errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                        return errMap;
                    }).collect(Collectors.toList());

            return responseEntity.body(errors);
        }

        if (cause != null && cause.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ve = (ConstraintViolationException) cause.getCause();

            List<Map<String, String>> errors = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String, String> errMap = new HashMap<>();
                        errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                        return errMap;
                    }).collect(Collectors.toList());

            return responseEntity.body(errors);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error occurred");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String > errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorList);
    }
}
