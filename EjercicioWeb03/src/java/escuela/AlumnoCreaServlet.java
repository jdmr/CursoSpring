/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package escuela;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jdmr
 */
@WebServlet(name = "AlumnoCreaServlet", urlPatterns = {"/alumno/crea"})
public class AlumnoCreaServlet extends HttpServlet {


    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new RuntimeException("No puede utilizarlo via GET");
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        List<Alumno> alumnos = (List<Alumno>)request.getSession().getAttribute("alumnos");
        if (nombre != null && nombre.trim().length() > 0 
                && apellido != null && nombre.trim().length() > 0
                && alumnos != null) {
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumnos.add(alumno);
            String mensaje = "El usuario "+alumno.getNombre()+" ha sido creado.";
            request.getSession().setAttribute("mensaje", mensaje);
        } else {
            System.out.println("Nombre: "+nombre);
            System.out.println("Apellido: "+apellido);
            System.out.println("alumnos: "+alumnos);
            throw new RuntimeException("No cumplio con los datos requeridos");
        }
        response.sendRedirect(request.getContextPath()+"/alumnos");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
