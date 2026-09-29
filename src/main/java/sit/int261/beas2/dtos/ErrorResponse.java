package sit.int261.beas2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;
    private int status;
    private List<ViolationResponse> violations;
}