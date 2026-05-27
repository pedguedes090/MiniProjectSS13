package rikkei.edu.miniprojectss13.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rikkei.edu.miniprojectss13.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class EmployeeService {

    private final List<Employee> employees = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    public EmployeeService() {
        employees.add(new Employee(idGenerator.getAndIncrement(), "Nguyen Van A", "Engineering", 15000000.0));
        employees.add(new Employee(idGenerator.getAndIncrement(), "Tran Thi B", "Human Resources", 12000000.0));
        employees.add(new Employee(idGenerator.getAndIncrement(), "Le Van C", "Finance", 13500000.0));
        employees.add(new Employee(idGenerator.getAndIncrement(), "Pham Thi D", "Marketing", 11000000.0));
        employees.add(new Employee(idGenerator.getAndIncrement(), "Hoang Van E", "Engineering", 18000000.0));
    }

    public List<Employee> getAllEmployees() {
        synchronized (employees) {
            return new ArrayList<>(employees);
        }
    }

    // TASK 2 Tìm nhân viên theo ID
    public Employee getById(Long id) {
        synchronized (employees) {
            return employees.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> {
                        log.warn("Không tìm thấy nhân viên với id: {}", id);
                        return new RuntimeException("Không tìm thấy nhân viên với id: " + id);
                    });
        }
    }

    // TASK 3 Thêm nhân viên mới (tự sinh ID)
    public Employee addEmployee(Employee payload) {
        synchronized (employees) {
            Employee created = new Employee(
                    idGenerator.getAndIncrement(),
                    payload.getFullName(),
                    payload.getDepartment(),
                    payload.getSalary()
            );
            employees.add(created);
            return created;
        }
    }

    // TASK 4 Cập nhật toàn bộ thông tin nhân viên
    public Employee updateEmployee(Long id, Employee payload) {
        synchronized (employees) {
            for (int i = 0; i < employees.size(); i++) {
                Employee current = employees.get(i);
                if (current.getId().equals(id)) {
                    Employee updated = new Employee(
                            id,
                            payload.getFullName(),
                            payload.getDepartment(),
                            payload.getSalary()
                    );
                    employees.set(i, updated);
                    log.info("Employee updated with id: {}", id);
                    return updated;
                }
            }
            log.warn("Không tìm thấy nhân viên với id: {}", id);
            throw new RuntimeException("Không tìm thấy nhân viên với id: " + id);
        }
    }

    // TASK 5 Xóa nhân viên theo ID
    public void deleteById(Long id) {
        synchronized (employees) {
            boolean removed = employees.removeIf(e -> e.getId().equals(id));
            if (!removed) {
                log.warn("Không tìm thấy nhân viên với id: {}", id);
                throw new RuntimeException("Không tìm thấy nhân viên với id: " + id);
            }
        }
    }
}