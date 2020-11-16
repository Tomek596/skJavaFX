package sample.handler;

import sample.dto.OperatorAuthenticationResultDTO;

@FunctionalInterface
public interface AuthenticationResultHandler {

    void handle(OperatorAuthenticationResultDTO resultDTO);
}
