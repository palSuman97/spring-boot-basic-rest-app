package learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.springboot.entity.Employee;
import learn.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int employeeId) {
	
		return ResponseEntity.ok(employeeService.getEmployee(employeeId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId, @RequestBody Employee updatedEmployee) {
		
		return ResponseEntity.ok(employeeService.updateEmployee(employeeId, updatedEmployee));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId) {
		return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
	}
}
