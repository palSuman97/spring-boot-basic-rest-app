package learn.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.springboot.entity.Employee;
import learn.springboot.exception.EmployeeNotFound;
import learn.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(int employeeId) {
		
		 if(employeeRepository.findById(employeeId).isPresent())
			 return employeeRepository.findById(employeeId).get();
		 else 
			 throw new EmployeeNotFound("Employee Id:= "+ employeeId+" Not Found :(");
	}

	public Employee updateEmployee(int employeeId, Employee updatedEmployee) {
		
		Optional<Employee> existingEmployeeOp = employeeRepository.findById(employeeId);
		
		if(existingEmployeeOp.isPresent()) {
			Employee existingEmployee = existingEmployeeOp.get();
			if(updatedEmployee.getName() != null)
				existingEmployee.setName(updatedEmployee.getName());
			if(updatedEmployee.getPosition() != null)
				existingEmployee.setPosition(updatedEmployee.getPosition());
			if(updatedEmployee.getSalary() != 0)
				existingEmployee.setSalary(updatedEmployee.getSalary());
			
			return employeeRepository.save(existingEmployee);
		}
		else {
			throw new EmployeeNotFound("Employee Id:= "+ employeeId+" Not Found :(");
		}
	}

	public String deleteEmployee(int employeeId) {
		
		Employee employee = getEmployee(employeeId);
		employeeRepository.delete(employee);
		return "Employee Id:= "+ employeeId + " Deleted Successfully :)";
	}

}
