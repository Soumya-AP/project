<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Employee List</h1>  
 <form:form action="searchemp" modelAttribute="emp">  
Employee ID:   ${empId} <br>  
First Name:    ${firstName} <br>  
Last Name:     ${lastName} <br>  
Age:           ${age} <br>  
 </form:form>  
   <br/>  
   <a href="/Assignment_MVC_spring">Go Back</a>  

