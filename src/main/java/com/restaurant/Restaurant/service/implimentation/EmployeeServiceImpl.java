package com.restaurant.Restaurant.service.implimentation;

import com.restaurant.Restaurant.entity.Employee;
import com.restaurant.Restaurant.repository.EmployeeRepository;
import com.restaurant.Restaurant.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public String generateNextEmpId() {
        Employee lastEmployee = employeeRepository.findTopByOrderByIdDesc();
        if (lastEmployee == null) {
            return "EMP01";
        }
        String lastId = lastEmployee.getId(); // e.g., EMP04
        int number = Integer.parseInt(lastId.substring(3)); // 04 → 4
        number++;
        return String.format("EMP%02d", number); // EMP05
    }
    @Override
    public Employee insertEmployee(Employee employee) {
        employee.setId(generateNextEmpId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByUserName(String username) {
        return employeeRepository.findByUserName(username);
    }

    @Override
    public Employee getEmployeeByContact(String contact) {
        return employeeRepository.findByContact(contact);
    }
}
