package com.restaurant.Restaurant.service;

import com.restaurant.Restaurant.entity.Employee;

public interface EmployeeService {
    Employee insertEmployee(Employee employee);
    Employee getEmployeeByUserName(String username);

    Employee getEmployeeByContact(String userName);
}
