package sit.int261.beas2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sit.int261.beas2.dtos.ErrorResponse;
import sit.int261.beas2.dtos.ViolationResponse;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Validation failed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        List<ViolationResponse> violations =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new ViolationResponse(
                                error.getField(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        ErrorResponse response = new ErrorResponse(
                "VALIDATION_FAILED",
                "Request validation failed",
                HttpStatus.BAD_REQUEST.value(),
                violations
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // 404 - Customer not found
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(
            CustomerNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
                "CUSTOMER_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // 404 - Inventory not found
    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryNotFound(
            InventoryNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
                "INVENTORY_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    // 409 - Inventory is already rented
    @ExceptionHandler(InventoryUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleInventoryUnavailable(
            InventoryUnavailableException ex) {

        ErrorResponse response = new ErrorResponse(
                "INVENTORY_UNAVAILABLE",
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}