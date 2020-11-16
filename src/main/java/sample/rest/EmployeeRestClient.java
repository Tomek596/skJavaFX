package sample.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.EmployeeDTO;
import sample.handler.SavedEmployeeHandler;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private static final String EMPLOYEES_URL = "http://localhost:8080/employees";

    private final RestTemplate restTemplate;

    public EmployeeRestClient(){
        restTemplate = new RestTemplate();
    }

    public List<EmployeeDTO> getEmployee(){
        ResponseEntity<EmployeeDTO[]> employeesResponseEntity = restTemplate.getForEntity(EMPLOYEES_URL, EmployeeDTO[].class);
        return Arrays.asList(employeesResponseEntity.getBody());
    }

    public void saveEmployee(EmployeeDTO dto, SavedEmployeeHandler handler) {
        ResponseEntity<EmployeeDTO> responseEntity = restTemplate.postForEntity(EMPLOYEES_URL, dto, EmployeeDTO.class);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            handler.handle();
        }else {
            //TODO implement
        }
    }
}
