package com.transaction.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.transaction.job.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}