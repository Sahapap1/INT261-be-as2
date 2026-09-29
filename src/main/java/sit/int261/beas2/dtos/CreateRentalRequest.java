package sit.int261.beas2.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRentalRequest {

    @NotNull(message = "must not be null")
    @Positive(message = "must be greater than 0")
    private Integer customerId;

    @NotNull(message = "must not be null")
    @Positive(message = "must be greater than 0")
    private Integer inventoryId;

    @NotNull(message = "must not be null")
    @Positive(message = "must be greater than 0")
    private Integer staffId;
}