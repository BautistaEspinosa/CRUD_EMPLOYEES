package com.example.CRUDEMPLOYEES.repository;

import com.example.CRUDEMPLOYEES.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
