package sit.int261.beas2.exceptions;

public class InventoryUnavailableException extends RuntimeException {

    public InventoryUnavailableException(Integer inventoryId) {
        super("Inventory is currently unavailable: " + inventoryId);
    }
}