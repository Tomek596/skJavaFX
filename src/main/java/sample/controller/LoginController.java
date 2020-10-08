package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.dto.OperatorCredentialsDTO;
import sample.factory.PopUpFactory;
import sample.rest.Authenticator;
import sample.rest.AuthenticatorImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String APP_FXML = "/fxml/app.fxml";
    private static final String APP_TITLE = "ERPSystem";
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

        OperatorCredentialsDTO dto = new OperatorCredentialsDTO();
        dto.setLogin(login);
        dto.setPassword(password);

        authenticator.authenticate(dto, (authenticationResult) -> {
            Platform.runLater(() -> {
                waitingPopUp.close();
                if (authenticationResult.isAuthenticated()) {
                    openAppAndCloseLoginStage();
                } else {
                    showIncorrectCredetialsMessage();
                }
            });
        });

    }

    private void openAppAndCloseLoginStage() {
        Stage appStage = new Stage();
        Parent appRoot = null;
        try {
            appRoot = FXMLLoader.load(getClass().getResource(APP_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(appRoot, 1024, 768);
        appStage.setTitle(APP_TITLE);
        appStage.setScene(scene);
        appStage.show();
        getStage().close();
    }

    private void showIncorrectCredetialsMessage() {
        //TODO
        System.out.println("Incorrect credentials");
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
