package sample.rest;

import sample.dto.UserCredentialsDto;

public interface Authenticator {

    void authenticate(UserCredentialsDto userCredentialsDto, AuthenticationResultHandler authenticationResultHandler);
}
