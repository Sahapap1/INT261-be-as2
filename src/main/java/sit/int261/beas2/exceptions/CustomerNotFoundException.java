package sit.int261.beas2.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer customerId) {
        super("Customer not found: " + customerId);
    }
}