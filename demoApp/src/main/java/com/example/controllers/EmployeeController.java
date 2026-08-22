package com.example.controllers;

import com.example.exceptionhandling.ResourceNotFoundException;
import com.example.model.Employee;
import com.example.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public List<Employee> employeeList() {
        return employeeRepository.findAll();
    }

    @GetMapping("/listOne/{id}")
    public ResponseEntity<Employee> readEmployee(@PathVariable(value = "id") Integer employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee emp) {
        employeeRepository.save(emp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId, @RequestBody Employee emp) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + employeeId));

        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setAge(emp.getAge());
        employee.setSalary(emp.getSalary());
        employee.setEducation(emp.getEducation());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
