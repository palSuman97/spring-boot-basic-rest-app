package learn.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import learn.springboot.entity.Employee;
import learn.springboot.exception.EmployeeNotFound;
import learn.springboot.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;

	Employee employee;
	Employee updatedEmployee;
	
	@Mock
	Employee mockedEmployee;
	
    @BeforeEach
    public void setUp() {
    	employee = Employee.builder().Id(1).name("Suman").position("Java Developer").salary(90000).build();
    	updatedEmployee = Employee.builder().Id(1).name("Suman_updated").position("Spring Boot Developer").salary(130000).build();
    }
	
	@Test
	public void addEmployeeTest() {
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.addEmployee(employee));
	}
	
	@Test
	public void getEmployeeTest() {
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		assertEquals(employee, employeeService.getEmployee(1));
	}
	
	@Test
	public void getEmployeeNotFoundTest() {
		when(employeeRepository.findById(1)).thenReturn(Optional.empty());
		EmployeeNotFound ex = assertThrows(EmployeeNotFound.class, () -> employeeService.getEmployee(1));
		assertEquals("Employee Id:= 1 Not Found :(", ex.getMessage());
	}
	
	@Test
	public void updateEmployeeTest() {
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		when(mockedEmployee.getName()).thenReturn("Suman_updated");
		when(mockedEmployee.getPosition()).thenReturn("Spring Boot Developer");
		when(mockedEmployee.getSalary()).thenReturn(130000);
		when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);		
		assertEquals(updatedEmployee, employeeService.updateEmployee(1, updatedEmployee));
	}
	
	@Test
	public void updateEmployeeNotFoundTest() {
		when(employeeRepository.findById(1)).thenReturn(Optional.empty());
		EmployeeNotFound ex = assertThrows(EmployeeNotFound.class, () -> employeeService.updateEmployee(1, updatedEmployee));
		assertEquals("Employee Id:= 1 Not Found :(", ex.getMessage());
	}
	
	@Test
	public void deleteEmployeeTest() {
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		assertEquals("Employee Id:= 1 Deleted Successfully :)", employeeService.deleteEmployee(1));
	}
}
