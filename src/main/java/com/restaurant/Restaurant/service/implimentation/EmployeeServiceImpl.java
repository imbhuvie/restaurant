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

    @Override
    public Employee insertEmployee(Employee employee) {
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
