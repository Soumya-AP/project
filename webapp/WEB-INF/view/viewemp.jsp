<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Employees List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Employee Id</th><th>First Name</th><th>Last Name</th><th>Age</th><th>Edit</th><th>Delete</th></tr>  
   <c:forEach var="emp" items="${list}">   
   <tr>  
   <td>${emp.empId}</td>  
   <td>${emp.firstName}</td>  
   <td>${emp.lastName}</td>  
   <td>${emp.age}</td>  
   <td><a href="editemp/${emp.empId}">Edit</a></td>  
   <td><a href="deleteemp/${emp.empId}">Delete</a></td>  
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   <a href="empform">Add New Employee</a>  
   