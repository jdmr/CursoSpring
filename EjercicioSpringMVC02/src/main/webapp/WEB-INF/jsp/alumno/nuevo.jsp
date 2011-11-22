<%-- 
    Document   : lista
    Created on : Nov 21, 2011, 11:44:12 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Alumno</title>
    </head>
    <body>
        <h1>Nuevo Alumno</h1>
        <s:url var="creaAlumno" value="/alumno/crea" />
        <form:form name="alumnoForm" modelAttribute="alumno" method="post" action="${creaAlumno}" enctype="multipart/form-data" >
            <table>
                <tbody>
                    <tr>
                        <td>Nombre:</td>
                        <td>
                            <form:input path="nombre" />
                            <form:errors path="nombre" cssStyle="color:red;" />
                        </td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td>
                            <form:input path="apellido" />
                            <form:errors path="apellido" cssStyle="color:red;" />
                        </td>
                    </tr>
                    <tr>
                        <td>Imagen:</td>
                        <td>
                            <input type="file" name="imagen" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>
