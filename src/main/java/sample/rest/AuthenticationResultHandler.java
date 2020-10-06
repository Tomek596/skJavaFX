package sample.rest;

@FunctionalInterface
public interface AuthenticationResultHandler {

    void handle(boolean authenticationResult);
}
