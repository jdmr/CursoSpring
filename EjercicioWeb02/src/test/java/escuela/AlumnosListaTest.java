/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package escuela;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.atMost;

/**
 *
 * @author jdmr
 */
public class AlumnosListaTest {

    public AlumnosListaTest() {
    }

    /**
     * Test of processRequest method, of class AlumnosLista.
     */
    @Test
    public void testProcessRequest() throws Exception {
        AlumnosLista controller = new AlumnosLista();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext servletContext = mock(ServletContext.class);
        ServletConfig servletConfig = mock(ServletConfig.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        when(request.getSession()).thenReturn(session);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        
        when(servletContext.getRequestDispatcher(eq("/alumnos.jsp"))).thenReturn(dispatcher);
        
        controller.init(servletConfig);
        controller.processRequest(request, response);
        
        verify(session, atMost(1));
    }

}
