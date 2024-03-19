package learn.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
