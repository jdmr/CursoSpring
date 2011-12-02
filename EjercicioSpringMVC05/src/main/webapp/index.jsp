<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escuela</title>
    </head>
    <body>
        <h1>Escuela</h1>
        <ul>
            <li><a href="<c:url value='/salon/lista'/>">Salones</a></li>
            <li><a href="<c:url value='/alumno/lista'/>">Alumnos</a></li>
        </ul>
    </body>
</html>
