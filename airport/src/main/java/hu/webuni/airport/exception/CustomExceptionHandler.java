package hu.webuni.airport.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NonUniqueIataException.class)
    public ProblemDetail handleNonUniqueIata(NonUniqueIataException ex){
        log.warn(ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setProperty("fieldErrors", ex.getBindingResult().getFieldErrors());

        return problem;
    }
}
