<%-- 
    Document   : lista
    Created on : Nov 21, 2011, 11:44:12 AM
    Author     : jdmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Salon</title>
    </head>
    <body>
        <h1>Nuevo Salon</h1>
        <c:url var="creaSalon" value="/salon/crea" />
        <form:form name="salonForm" modelAttribute="salon" method="post" action="${creaSalon}" >
            <table>
                <tbody>
                    <tr>
                        <td>Nombre:</td>
                        <td><form:input path="nombre" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>
    </body>
</html>
