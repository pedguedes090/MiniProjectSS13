package rikkei.edu.miniprojectss13.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rikkei.edu.miniprojectss13.model.Employee;
import rikkei.edu.miniprojectss13.service.EmployeeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("GET /api/employees called");
        List<Employee> list = employeeService.getAllEmployees();
        return ResponseEntity.ok(list);
    }
}
