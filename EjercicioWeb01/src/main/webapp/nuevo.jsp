<%-- 
    Document   : nuevo
    Created on : Nov 28, 2011, 5:45:49 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Alumno</title>
    </head>
    <body>
        <h1>Nuevo Alumno</h1>
        <form name="alumnoForm" id="alumnoForm" action="crea.jsp" method="post">
            <table>
                <tbody>
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" /></td>
                    </tr>
                    <tr>
                        <td>Apellido:</td>
                        <td><input type="text" name="apellido" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Crear" />
                            <a href="alumnos.jsp">Cancelar</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
