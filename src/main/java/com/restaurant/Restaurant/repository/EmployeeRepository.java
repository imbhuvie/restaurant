package com.restaurant.Restaurant.repository;

import com.restaurant.Restaurant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByUserName(String username);

    Employee findByContact(String contact);
}
