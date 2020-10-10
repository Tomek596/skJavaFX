package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dto.EmployeeDTO;
import sample.rest.EmployeeRestClient;
import sample.table.EmployeeTableModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppController implements Initializable {

    private final EmployeeRestClient employeeRestClient;

    public AppController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);                                   //ustawianie rozmiaru kolumn na rowne szerokosci

        TableColumn firstNameColumn = new TableColumn("First name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("firstName"));

        TableColumn lastNameColumn = new TableColumn("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("lastName"));

        TableColumn salaryColumn = new TableColumn("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("salary"));
        employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, salaryColumn);

        ObservableList<EmployeeTableModel> data = FXCollections.observableArrayList();

        loadEmployeeData(data);

        employeeTableView.setItems(data);
    }

    private void loadEmployeeData(ObservableList<EmployeeTableModel> data) {
        Thread thread = new Thread(() -> {
            List<EmployeeDTO> employees = employeeRestClient.getEmployee();
            data.addAll(employees.stream()
                    .map(EmployeeTableModel::of)
                    .collect(Collectors.toList()));
        });
        thread.start();
    }
}

