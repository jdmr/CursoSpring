<%-- 
    Document   : lista
    Created on : Jun 7, 2012, 11:14:02 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edita Alumno</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />" type="text/css">
    </head>
    <body>
        <div class="container-fluid" >
            <h1>Edita Alumno</h1>
            <c:if test="${not empty mensaje}">
                <div class="alert alert-block ${estiloMensaje}">
                    <a class="close" data-dismiss="alert" href="#">×</a>
                    <p>${mensaje}</p>
                </div>
            </c:if>
            <c:url var="actualiza" value="/alumno/actualiza" />
            <form:form name="alumnoForm" modelAttribute="alumno" method="post" action="${actualiza}" >
                <form:hidden path="matricula" />
                <fieldset>
                    <div class="row-fluid control-group">
                        <label for="matricula">Matrícula:</label>
                        <form:input path="matricula" disabled="true" />
                    </div>
                    <div class="row-fluid control-group">
                        <label for="nombre">Nombre:</label>
                        <form:input path="nombre" />
                    </div>
                    <div class="row-fluid control-group">
                        <label for="apellido">Apellido:</label>
                        <form:input path="apellido" />
                    </div>
                    <div class="row-fluid control-group">
                        <button type="submit" class="btn btn-primary btn-large"><i class="icon-user icon-white"></i> Crear</button>
                        <a href="<c:url value='/alumno' />" class="btn btn-large"><i class="icon-remove"></i>Regresar</a>
                    </div>
                </fieldset>
            </form:form>
            <script src="<c:url value='/js/jquery-1.7.2.min.js' />"></script>
            <script src="<c:url value='/js/bootstrap.min.js' />"></script>
            <script src="<c:url value='/js/app.js' />"></script>
        </div>
    </body>
</html>
