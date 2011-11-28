<%-- 
    Document   : alumnos
    Created on : Nov 28, 2011, 5:33:54 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="escuela.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
    </head>
    <body>
        <h1>Alumnos</h1>
        <p><a href="nuevo.jsp">Nuevo</a></p>
        <%
        List<Alumno> alumnos = (List<Alumno>) request.getSession().getAttribute("alumnos");
        if (alumnos == null) {
            alumnos = new ArrayList<Alumno>();
            Alumno alumno = new Alumno();
            alumno.setNombre("David");
            alumno.setApellido("Mendoza");
            alumnos.add(alumno);
            
            alumno = new Alumno();
            alumno.setNombre("Dulce");
            alumno.setApellido("Alvarado");
            alumnos.add(alumno);
            request.getSession().setAttribute("alumnos",alumnos);
        }
        %>
        
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                </tr>
            </thead>
            <tbody>
        
        <%            
        for(Alumno alumno: alumnos) {
        %>
        
                <tr>
                    <td><%= alumno.getNombre() %></td>
                    <td><%= alumno.getApellido() %></td>
                </tr>
            
        
        <%            
        }
        %>
            </tbody>
        </table>
        
    </body>
</html>
