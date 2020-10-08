package sample.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.OperatorAuthenticationResultDTO;
import sample.dto.OperatorCredentialsDTO;

public class AuthenticatorImpl implements Authenticator {

    private static final String AUTHENTICATION_URL = "http://localhost:8080/verify_operator_credentials";

    private final RestTemplate restTemplate;


    public AuthenticatorImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public void authenticate(OperatorCredentialsDTO operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {

        Runnable authenticationTask = () -> {
            processAuthentication(operatorCredentialsDTO, authenticationResultHandler);
        };
        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();

    }

    private void processAuthentication(OperatorCredentialsDTO operatorCredentialsDTO, AuthenticationResultHandler authenticationResultHandler) {

        ResponseEntity<OperatorAuthenticationResultDTO> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialsDTO, OperatorAuthenticationResultDTO.class);
        authenticationResultHandler.handle(responseEntity.getBody());
    }
}
