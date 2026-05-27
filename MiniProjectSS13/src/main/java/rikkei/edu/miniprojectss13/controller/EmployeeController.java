package rikkei.edu.miniprojectss13.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // TASK 2 GET chi tiết 1 nhân viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        log.info("GET /api/employees/{} called", id);
        try {
            Employee emp = employeeService.getById(id);
            return ResponseEntity.ok(emp);              // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();   // 404 Not Found
        }
    }

    // TASK 3 Thêm nhân viên mới
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee payload) {
        log.info("POST /api/employees called");
        Employee created = employeeService.addEmployee(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // TASK 5 Xóa nhân viên theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/employees/{} called", id);
        try {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
}