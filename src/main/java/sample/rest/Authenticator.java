package sample.rest;

import sample.dto.OperatorCredentialsDTO;

public interface Authenticator {

    void authenticate(OperatorCredentialsDTO operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler);
}
