package com.springMVC;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    
    public void deleteById(int id)
    {
    	repository.deleteById(id);
    }
    
    public void save(Employee employee)
    {
    	 repository.save(employee);
    }
    
    public Optional<Employee> getById(int id)
    {
    	Optional<Employee> result = repository.findById(id);
        return result;
    }
    
    
    
    public List<Employee> getEmployees()
    {
    	List<Employee> list = repository.findAll();
        
        return list;
    }

	public void update(Employee emp) {
		repository.saveAndFlush(emp);
		
	}
}

