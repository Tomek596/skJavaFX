package sample.table;

import javafx.beans.property.SimpleStringProperty;
import sample.dto.EmployeeDTO;

public class EmployeeTableModel {

    private final SimpleStringProperty firstName;                                                                       //typ String do tabel w javafx
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty salary;

    public EmployeeTableModel(String firstName, String lastName, String salary){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleStringProperty(salary);
    }

    public static EmployeeTableModel of(EmployeeDTO dto){
        return new EmployeeTableModel(dto.getFirstName(),dto.getLastName(),dto.getSalary());
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSalary() {
        return salary.get();
    }

    public SimpleStringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }
}
