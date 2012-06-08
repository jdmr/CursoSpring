<%-- 
    Document   : lista
    Created on : Jun 7, 2012, 11:14:02 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lista de Alumnos</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />" type="text/css">
    </head>
    <body>
        <div class="container-fluid" >
            <h1>Lista de Alumnos</h1>
            <a href="<c:url value='/alumno/nuevo' />" class="btn btn-primary btn-large">Nuevo</a>
            <c:if test="${not empty mensaje}">
                <div class="alert alert-block ${estiloMensaje}">
                    <a class="close" data-dismiss="alert" href="#">×</a>
                    <p>${mensaje}</p>
                </div>
            </c:if>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Matrícula</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${alumnos}" var="alumno">
                        <tr>
                            <td>${alumno.matricula}</td>
                            <td>${alumno.nombre}</td>
                            <td>${alumno.apellido}</td>
                            <td>
                                <a href='<c:url value="/alumno/edita/${alumno.matricula}" />'>Edita</a>
                                <a href='<c:url value="/alumno/elimina/${alumno.matricula}" />'>Elimina</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <script src="<c:url value='/js/jquery-1.7.2.min.js' />"></script>
            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
            <script src="<c:url value='/js/app.js' />"></script>
        </div>
    </body>
</html>
