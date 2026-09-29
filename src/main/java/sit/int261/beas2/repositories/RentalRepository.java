package sit.int261.beas2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int261.beas2.entities.Rental;

interface RentalRepository extends JpaRepository<Rental, Integer> {
}
