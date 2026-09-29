package sit.int261.beas2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sit.int261.beas2.dtos.CreateRentalRequest;
import sit.int261.beas2.entities.Customer;
import sit.int261.beas2.entities.Inventory;
import sit.int261.beas2.entities.Rental;
import sit.int261.beas2.entities.Staff;
import sit.int261.beas2.exceptions.CustomerNotFoundException;
import sit.int261.beas2.exceptions.InventoryNotFoundException;
import sit.int261.beas2.exceptions.InventoryUnavailableException;
import sit.int261.beas2.repositories.CustomerRepository;
import sit.int261.beas2.repositories.InventoryRepository;
import sit.int261.beas2.repositories.RentalRepository;
import sit.int261.beas2.repositories.StaffRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public Rental createRental(CreateRentalRequest request) {

        // 1. ตรวจสอบ customer
        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() ->
                        new CustomerNotFoundException(request.getCustomerId())
                );

        // 2. ตรวจสอบ inventory
        Inventory inventory = inventoryRepository
                .findById(request.getInventoryId())
                .orElseThrow(() ->
                        new InventoryNotFoundException(request.getInventoryId())
                );

        // 3. ตรวจสอบว่า inventory กำลังถูกเช่าอยู่หรือไม่
        boolean isActiveRental =
                rentalRepository.existsByInventoryAndReturnDateIsNull(inventory);

        if (isActiveRental) {
            throw new InventoryUnavailableException(request.getInventoryId());
        }

        // 4. หา staff
        Staff staff = staffRepository
                .findById(request.getStaffId())
                .orElseThrow(() ->
                        new RuntimeException("Staff not found: " + request.getStaffId())
                );

        // 5. สร้าง rental
        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setReturnDate(null);
        rental.setLastUpdate(LocalDateTime.now());

        // 6. บันทึกลง database
        return rentalRepository.save(rental);
    }
}