<%-- 
    Document   : alumnos
    Created on : Nov 28, 2011, 1:58:53 PM
    Author     : jdmr
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
