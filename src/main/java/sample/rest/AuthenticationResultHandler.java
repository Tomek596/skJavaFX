package sample.rest;

import sample.dto.OperatorAuthenticationResultDTO;

@FunctionalInterface
public interface AuthenticationResultHandler {

    void handle(OperatorAuthenticationResultDTO resultDTO);
}
