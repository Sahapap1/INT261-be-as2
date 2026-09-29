package sit.int261.beas2.exceptions;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(Integer inventoryId) {
        super("Inventory not found: " + inventoryId);
    }
}