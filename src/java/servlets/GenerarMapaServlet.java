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
import model.Inmueble;
import repository.InmueblesRepository;

/**
 *
 * @author Mikael
 */
public class GenerarMapaServlet extends HttpServlet {

    private String promocion;
    private String provincia;
    private String localidad;
    private String tipo_inmueble;

    private InmueblesRepository repo = new InmueblesRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        promocion = request.getParameter("promocion");
        provincia = request.getParameter("provincia");
        localidad = request.getParameter("localidad");
        tipo_inmueble = request.getParameter("tipo_inmueble");
        boolean hayDatos = false;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (Inmueble property : repo.getInmuebles(provincia, localidad, promocion, tipo_inmueble, true)) {
                hayDatos = true;
                out.print(property.getInm_id() + "##"
                        + property.getInm_latitud_longitud() + "##"
                        + property.getInm_m2_construidos() + "##"
                        + property.getInm_promocion() + "##"
                        + property.getInm_tipo_inmueble() +"||");
            }
            if(hayDatos)out.print("]");

        }

    }
}
