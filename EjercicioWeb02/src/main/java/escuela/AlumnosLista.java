package escuela;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "AlumnosLista", urlPatterns = {"/alumnos", "/alumnos/lista"})
public class AlumnosLista extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando al servlet");
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
            request.getSession().setAttribute("alumnos", alumnos);
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
        System.out.println("Saliendo del servlet");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
