<%-- 
    Document   : nuevo
    Created on : Nov 28, 2011, 3:32:18 PM
    Author     : jdmr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Alumno</title>
    </head>
    <body>
        <h1>Nuevo Alumno</h1>
        <form name="alumnoForm" id="alumnoForm" action="<c:url value='/alumno/crea'/>" method="post">
            <table>
                <tbody>
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" id="nombre" value="${alumno.nombre}"</td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td><input type="text" name="apellido" id="apellido" value="${alumno.apellido}"</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Crear" />
                            <a href="<c:url value='/alumnos' />" >Regresar</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <script type="text/javascript">
            document.alumnoForm.nombre.focus();
        </script>
    </body>
</html>
