<%-- 
    Document   : index
    Created on : Nov 22, 2011, 11:57:54 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><a href="<%= request.getContextPath() %>/lista">Lista de Alumnos</a></p>
    </body>
</html>
