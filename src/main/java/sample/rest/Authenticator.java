package sample.rest;

import sample.dto.OperatorCredentialsDTO;
import sample.handler.AuthenticationResultHandler;

public interface Authenticator {

    void authenticate(OperatorCredentialsDTO operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler);
}
