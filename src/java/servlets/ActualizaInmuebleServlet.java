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
public class ActualizaInmuebleServlet extends HttpServlet {

    private String promocion;
    private String localidad;
    private String tipoInmueble;
    private String id;
    private String tipologiaPromocion;
    private String ccaa;
    private String provinciaPrinex;
    private String cp;
    private String tipoProducto;
    private String adaptadorbde;
    private String latitud;
    private String longitud;
    private String refCatastro;
    private String superficieCons;
    private String descripcion;
    private String perimetroJunio;

    private InmueblesRepository repo = new InmueblesRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        promocion = request.getParameter("promocion");
        provinciaPrinex = request.getParameter("provinciaPrinex");
        localidad = request.getParameter("localidad");
        tipoInmueble = request.getParameter("tipoInmueble");
        id = request.getParameter("id");
        tipologiaPromocion = request.getParameter("tipologiaPromocion");
        ccaa = request.getParameter("ccaa").trim();
        cp = request.getParameter("cp");
        tipoProducto = request.getParameter("tipoProducto");
        adaptadorbde = request.getParameter("adaptadorbde");
        latitud = request.getParameter("latitud");
        longitud = request.getParameter("longitud");
        refCatastro = request.getParameter("refCatastro").trim();
        superficieCons = request.getParameter("superficieCons");
        descripcion = request.getParameter("descripcion").trim();
        perimetroJunio = request.getParameter("perimetroJunio");

        Inmueble property = new Inmueble(Integer.parseInt(id),
                promocion,
                tipologiaPromocion,
                tipoInmueble,
                tipoProducto,
                adaptadorbde,
                descripcion,
                localidad,
                Integer.parseInt(cp),
                provinciaPrinex,
                ccaa,
                latitud + " " + longitud,
                refCatastro,
                Float.parseFloat(superficieCons),
                perimetroJunio);

        response.setContentType("text/html;charset=UTF-8");
        int status = repo.actualizaInmueble(property);
        try (PrintWriter out = response.getWriter()) {
           out.print(status);
        }

    }
}
