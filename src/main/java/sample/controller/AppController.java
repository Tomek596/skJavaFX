package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.table.EmployeeTableModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {


    @FXML
    private Pane appPane;
    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    public AppController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadview();
    }

    private void loadview() {
        try {
            BorderPane borderPane = FXMLLoader.load(getClass().getResource("/fxml/employee.fxml"));                     //Wstrzykiwanie innego fxmla
            appPane.getChildren().add(borderPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

