<%-- 
    Document   : crea
    Created on : Nov 28, 2011, 5:50:39 AM
    Author     : jdmr
--%>

<%@page import="escuela.Alumno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Alumno</title>
    </head>
    <body>
        <% 
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            List<Alumno> alumnos = (List<Alumno>) request.getSession().getAttribute("alumnos");
            if (nombre != null && apellido != null && alumnos != null) {
                Alumno alumno = new Alumno();
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                alumnos.add(alumno);
        %>
        <h1>El alumno(a) <%= alumno.getNombre() %> ha sido creado!</h1>
        <%
            } else {
                System.out.println("Nombre:   "+nombre);
                System.out.println("Apellido: "+apellido);
                System.out.println("Alumnos:  "+alumnos);
        %>
        <h1>El alumno no pudo ser creado...</h1>
        <%
            }
        %>
        <p><a href="alumnos.jsp">Regresar</a></p>
    </body>
</html>
