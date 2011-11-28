<%-- 
    Document   : alumnos
    Created on : Nov 28, 2011, 5:33:54 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="escuela.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
    </head>
    <body>
        <h1>Alumnos</h1>
        <p><a href="nuevo.jsp">Nuevo</a></p>
        
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                </tr>
            </thead>
            <tbody>

            <c:forEach items="${alumnos}" var="alumno">
        
                <tr>
                    <td>${alumno.nombre}</td>
                    <td>${alumno.apellido}</td>
                </tr>
            </c:forEach>
        
            </tbody>
        </table>
        
    </body>
</html>
