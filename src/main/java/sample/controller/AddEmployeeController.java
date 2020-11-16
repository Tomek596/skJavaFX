package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.dto.EmployeeDTO;
import sample.factory.PopUpFactory;
import sample.rest.EmployeeRestClient;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    private final PopUpFactory popUpFactory;
    private final EmployeeRestClient employeeRestClient;

    @FXML
    private BorderPane addEmployeeBorderPane;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Button saveButton;

    public AddEmployeeController() {
        popUpFactory = new PopUpFactory();
        employeeRestClient = new EmployeeRestClient();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCancelButton();
        initializeSaveButton();
    }

    private void initializeSaveButton() {
        saveButton.setOnAction((x) -> {
            EmployeeDTO dto = createEmployeeDTO();
            Stage waitingPopUp = popUpFactory.createWaitingPopUp("Conecting to the server...");
            waitingPopUp.show();
            employeeRestClient.saveEmployee(dto, () -> {
                waitingPopUp.close();
                Stage infoPopUp = popUpFactory.createInfoPopUp("Pracownik został zapisany", () -> {
                    getStage().close();
                });
                infoPopUp.show();
            });
        });
    }

    private EmployeeDTO createEmployeeDTO() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String salary = salaryTextField.getText();

        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setSalary(salary);

        return dto;
    }

    private void initializeCancelButton() {
        cancelButton.setOnAction((x) -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return (Stage) addEmployeeBorderPane.getScene().getWindow();
    }
}
