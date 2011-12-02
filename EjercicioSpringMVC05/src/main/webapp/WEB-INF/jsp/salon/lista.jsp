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
        <title>Salones</title>
    </head>
    <body>
        <h1>Lista de Salones</h1>
        <p>
            <c:url var="nuevoSalon" value="/salon/nuevo" />
            <a href="${nuevoSalon}">Nuevo</a>
        </p>
        <c:if test="${salones != null}">
            <p>Mostrando lista de salones...</p>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${salones}" var="salon">
                        <tr>
                            <td>${salon.nombre}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                
        </c:if>
    </body>
</html>
