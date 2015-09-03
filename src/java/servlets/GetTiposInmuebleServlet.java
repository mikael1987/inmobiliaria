/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.InmueblesRepository;

/**
 *
 * @author Mikael
 */
public class GetTiposInmuebleServlet extends HttpServlet {

    private String promocion;
    private String provincia;
    private String localidad;
    private InmueblesRepository repo = new InmueblesRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        promocion = request.getParameter("promocion");
        provincia = request.getParameter("provincia");
        localidad = request.getParameter("localidad");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             out.print("<option value=''></option>");
            for (String tipo : repo.getTiposInmueble(promocion,localidad,provincia)) {
                out.print("<option value='" + tipo + "'>" + tipo + "</option>");
            }
           
        }

    }

}
