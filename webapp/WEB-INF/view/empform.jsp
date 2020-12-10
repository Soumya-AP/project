<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Add New Employee</h1>  
       <form:form method="post" action="save">    
        <table >    
         <tr>    
          <td>Employee Id : </td>   
          <td><form:input path="empId"  /></td>  
         </tr>    
         <tr>    
          <td>First Name :</td>    
          <td><form:input path="firstName" /></td>  
         </tr>   
         <tr>    
          <td>Last Name :</td>    
          <td><form:input path="lastName" /></td>  
         </tr>  
         <tr>    
          <td>Age :</td>    
          <td><form:input path="age" /></td>  
         </tr>  
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>   