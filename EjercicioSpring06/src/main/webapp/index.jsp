<%-- 
    Document   : index
    Created on : Jun 7, 2012, 11:14:02 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <a href="<c:url value='/alumno' />">Alumnos</a>
    </body>
</html>
