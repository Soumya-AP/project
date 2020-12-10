package com.miniProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miniProject.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	Optional<Employee> findByName(String name);
	
	@Query(value="select * from MiniProject.employee where id in"
			+ "(select employee_id from MiniProject.employee_role where role_id =3)", nativeQuery=true)
	List<Employee> getAdmins();
	
	@Query(value="select * from MiniProject.employee where id in"
			+ "(select employee_id from MiniProject.employee_role where role_id =2)", nativeQuery=true)
	List<Employee> getSuperAdmins();
	
	//@Query(value="select email from MiniProject.employee where id in"
	//		+ "(select employee_id from MiniProject.employee_role where role_id =1)", nativeQuery=true)
	
	
	Boolean existsByName(String name);

	Boolean existsByEmail(String email);

	Optional<Employee> findByEmail(String email);
}
