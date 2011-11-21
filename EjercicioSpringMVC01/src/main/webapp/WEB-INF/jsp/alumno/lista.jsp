<%-- 
    Document   : lista
    Created on : Nov 21, 2011, 11:44:12 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
    </head>
    <body>
        <h1>Lista de Alumnos</h1>
        <p>
            <c:url var="nuevoAlumno" value="/alumno/nuevo" />
            <a href="${nuevoAlumno}">Nuevo</a>
        </p>
        <c:if test="${alumnos != null}">
            <p>Mostrando lista de alumnos...</p>
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
                
        </c:if>
    </body>
</html>
