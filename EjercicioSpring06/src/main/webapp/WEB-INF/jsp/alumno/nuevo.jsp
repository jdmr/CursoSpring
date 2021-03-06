<%-- 
    Document   : lista
    Created on : Jun 7, 2012, 11:14:02 AM
    Author     : J. David Mendoza <jdmendoza@um.edu.mx>
--%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s"    uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nuevo Alumno</title>
        <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/bootstrap-responsive.min.css' />" type="text/css">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />" type="text/css">
    </head>
    <body>
        <div class="container-fluid" >
            <h1>Nuevo Alumno</h1>
            <c:url var="crea" value="/alumno/crea" />
            <form:form name="alumnoForm" modelAttribute="alumno" method="post" action="${crea}" >
                <form:errors path="*">
                    <div class="alert alert-block alert-error fade in" role="status">
                        <a class="close" data-dismiss="alert">×</a>
                        <h1>Ha ocurrido un error</h1>
                        <c:forEach items="${messages}" var="message">
                            <p>${message}</p>
                        </c:forEach>
                    </div>
                </form:errors>
                <fieldset>
                    <s:bind path="alumno.matricula">
                        <div class="row-fluid control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="matricula">Matrícula:</label>
                            <form:input path="matricula" required="true" />
                        </div>
                    </s:bind>
                    <s:bind path="alumno.nombre">
                        <div class="row-fluid control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="nombre">Nombre:</label>
                            <form:input path="nombre" />
                        </div>
                    </s:bind>
                    <s:bind path="alumno.apellido">
                        <div class="row-fluid control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="apellido">Apellido:</label>
                            <form:input path="apellido" />
                        </div>
                    </s:bind>
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
