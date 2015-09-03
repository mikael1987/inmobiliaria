/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.InmueblesRepository;

/**
 *
 * @author Mikael
 */
public class GetPromocionesServlet extends HttpServlet {

    private String provincia;
    private String localidad;
    private InmueblesRepository repo = new InmueblesRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        provincia = request.getParameter("provincia");
        localidad = request.getParameter("localidad");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             out.print("<option value=''></option>");
            for (String promo : repo.getPromos(provincia, localidad)) {
                out.print("<option value='" + promo + "'>" + promo + "</option>");
            }
           
        }

    }

}
