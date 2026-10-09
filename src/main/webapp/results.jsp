<%@include file="head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><body>
<div class="container-fluid">
    <h2>Search Results: </h2>
   <table class ="table table-striped table-bordered">
           <thead>

           <tr>
               <th>Name</th>
               <th>Email</th>
               <th>Username</th>
           </tr>
           </thead>

           <tbody>
           <c:forEach var="user" items="${users}">
           <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.userName}</td>
           </tr>
           </c:forEach>
           </tbody>
       </table>


</div>

</body>
</html>
