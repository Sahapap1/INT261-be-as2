package sit.int261.beas2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sit.int261.beas2.dtos.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(
            CustomerNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                "CUSTOMER_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleInventoryNotFound(
            InventoryNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                "INVENTORY_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(InventoryUnavailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleInventoryUnavailable(
            InventoryUnavailableException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                "INVENTORY_UNAVAILABLE",
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
}