package hu.webuni.hr.acsaifz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problem.setTitle(String.format("%s not found", exception.getResourceName()));
        problem.setType(URI.create(String.format("/%s/not-found", exception.getResourceName().toLowerCase())));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ex.getBody();
        problem.setType(URI.create("/bad-request"));
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("violations", getViolations(ex));
        return problem;
    }

    private List<Violation> getViolations(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue()))
                .toList();
    }


    private record Violation(String field, String message, Object rejectedValue) {
    }

}


