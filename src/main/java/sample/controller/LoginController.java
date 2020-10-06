package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.dto.UserCredentialsDto;
import sample.factory.PopUpFactory;
import sample.rest.Authenticator;
import sample.rest.AuthenticatorImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private PopUpFactory popUpFactory;
    private Authenticator authenticator;

    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField loginTextField;

    public LoginController() {
        popUpFactory = new PopUpFactory();
        authenticator = new AuthenticatorImpl();
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login init");

        initializeExitButton();
        initializeLoginButton();
    }

    private void initializeLoginButton() {
        loginButton.setOnAction((x) -> {
            performAuthentication();
        });
    }

    private void performAuthentication() {
        Stage waitingPopUp = popUpFactory.createWaitingPopUp("Connecting to the server...");
        waitingPopUp.show();

        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        UserCredentialsDto dto = new UserCredentialsDto();
        dto.setLogin(login);
        dto.setPassword(password);

        authenticator.authenticate(dto, (authenticationResult) -> {
            Platform.runLater(()->waitingPopUp.close());
            System.out.println("autoryzacja: " + authenticationResult);
        });

    }

    private void initializeExitButton() {
        exitButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return (Stage) loginAnchorPane.getScene().getWindow();
    }
}
