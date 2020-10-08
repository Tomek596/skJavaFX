package sample.dto;

import lombok.Data;

@Data
public class OperatorAuthenticationResultDTO {

    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;

}
