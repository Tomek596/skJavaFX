package sample.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.EmployeeDTO;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private static final String GET_EMPLOYEES_URL = "http://localhost:8080/employees";

    private final RestTemplate restTemplate;

    public EmployeeRestClient(){
        restTemplate = new RestTemplate();
    }

    public List<EmployeeDTO> getEmployee(){
        ResponseEntity<EmployeeDTO[]> employeesResponseEntity = restTemplate.getForEntity(GET_EMPLOYEES_URL, EmployeeDTO[].class);
        return Arrays.asList(employeesResponseEntity.getBody());
    }
}
