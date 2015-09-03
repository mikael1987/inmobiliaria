/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.InmueblesRepository;

/**
 *
 * @author Mikael
 */
public class GetLocalidadesServlet extends HttpServlet {

    private String provincia;
    private InmueblesRepository repo = new InmueblesRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        provincia = request.getParameter("provincia");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             out.print("<option value=''></option>");
            for (String localidad : repo.getCities(provincia)) {
                out.print("<option value='" + localidad + "'>" + localidad + "</option>");
            }
            
        }

    }
}
