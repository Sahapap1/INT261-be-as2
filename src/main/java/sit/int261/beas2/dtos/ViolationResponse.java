package sit.int261.beas2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ViolationResponse {

    private String field;
    private String message;
}