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
public class GetInmueblesServlet extends HttpServlet {

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
        
        System.out.println("BUFFER:"+response.getBufferSize());

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("<table name=\"tablaInmuebles\" id=\"tablaInmuebles\" class=\"table table-striped table-bordered table-condensed table-responsive\">");
            out.print("<thead><th>Referencia</th><th>Localidad</th><th>C.P</th><th>Promoción</th><th>Tipo Inmueble</th><th>Metros Construidos</th><th>Ver</th></thead><tbody>");
            for (Inmueble property : repo.getInmuebles(provincia, localidad, promocion, tipo_inmueble,false)) {

                out.print("<tr><td>"+property.getInm_id()+"</td><td>" + property.getInm_municipio_ine() + "</td><td>" + property.getInm_cp() + "</td><td>" + property.getInm_promocion() + "</td><td>" + property.getInm_tipo_inmueble() + "</td><td>" + property.getInm_m2_construidos() + "</td><td><a href=\"#\"onClick=\"window.open('detallepropiedad.jsp?id="+property.getInm_id()+"','_blank')\" class=\"btn btn-info\" role=\"button\">Ficha</a></td></tr>");
            }
            out.print("</tbody></table>");

        }

    }

}
