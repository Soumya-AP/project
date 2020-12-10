package com.springMVC;

     
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;    
    
@Controller 
public class EmployeeController {    
    @Autowired    
    EmployeeService service;
    
    @RequestMapping("/empform")    
    public String showform(Model m){    
        m.addAttribute("command", new Employee());  
        return "empform";   
    }    
     
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("emp") Employee emp){    
    	service.save(emp);    
        return "redirect:/viewemp";  
    }    
      
    @RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<Employee> list=service.getEmployees();    
        m.addAttribute("list",list);  
        return "viewemp";    
    }    
    
    @RequestMapping(value="/searchemp",method=RequestMethod.GET)    
    public String searchemp(@RequestParam int empId,Model m)
    {    
    	Optional<Employee> emp=service.getById(empId);    
    	m.addAttribute("empId",emp.get().getEmpId());  
    	m.addAttribute("firstName", emp.get().getFirstName());
    	m.addAttribute("lastName",emp.get().getLastName());
    	m.addAttribute("age",emp.get().getAge());
        return"searchemp";  
    }    
       
    @RequestMapping(value="/editemp/{empId}")    
    public String edit(@PathVariable int empId, Model m){    
        Optional<Employee> emp=service.getById(empId);    
        m.addAttribute("command",emp);  
        return "empeditform";    
    }    
      
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") Employee emp){    
    	service.update(emp);    
        return "redirect:/viewemp";    
    }  
    
    @RequestMapping(value="/deleteemp/{empId}",method = RequestMethod.GET)    
    public String delete(@PathVariable int empId){    
    	service.deleteById(empId);    
        return "redirect:/viewemp";    
    }    
}  