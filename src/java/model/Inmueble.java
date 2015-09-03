package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikael
 */
public class Inmueble {

    private int inm_id;
    private String inm_promocion;
    private String inm_tipologia_promocion;
    private String inm_tipo_inmueble;
    private String inm_tipo_producto;
    private String inm_adaptado_circular_bde;
    private String inm_descripcion;
    private String inm_municipio_ine;
    private int inm_cp;
    private String inm_provincia_prinex;
    private String inm_ccaa;
    private String inm_latitud_longitud;
    private String inm_referencia_catastro;
    private float inm_m2_construidos;
    private String inm_perimetro_junio_15;
    private String inm_latitud;
    private String inm_longitud;

    public Inmueble(int inm_id, String inm_promocion, String inm_tipologia_promocion, String inm_tipo_inmueble, String inm_tipo_producto, String inm_adaptado_circular_bde, String inm_descripcion, String inm_municipio_ine, int inm_cp, String inm_provincia_prinex, String inm_ccaa, String inm_latitud_longitud, String inm_referencia_catastro, float inm_m2_construidos, String inm_perimetro_junio_15) {
        this.inm_id = inm_id;
        this.inm_promocion = inm_promocion;
        this.inm_tipologia_promocion = inm_tipologia_promocion;
        this.inm_tipo_inmueble = inm_tipo_inmueble;
        this.inm_tipo_producto = inm_tipo_producto;
        this.inm_adaptado_circular_bde = inm_adaptado_circular_bde;
        this.inm_descripcion = inm_descripcion;
        this.inm_municipio_ine = inm_municipio_ine;
        this.inm_cp = inm_cp;
        this.inm_provincia_prinex = inm_provincia_prinex;
        this.inm_ccaa = inm_ccaa;
        this.inm_latitud_longitud = inm_latitud_longitud;
        this.inm_referencia_catastro = inm_referencia_catastro;
        this.inm_m2_construidos = inm_m2_construidos;
        this.inm_perimetro_junio_15 = inm_perimetro_junio_15;
        setInm_coordinates();
    }

    public Inmueble() {

    }

    public void setInm_coordinates() {
        if (!getInm_latitud_longitud().equals("") && getInm_latitud_longitud().split(" ").length == 2 ) {
            String coordinates[] = getInm_latitud_longitud().split(" ");
            inm_latitud = coordinates[0];
            inm_longitud = coordinates[1];
        }
    }

    public String getInm_promocion() {
        return inm_promocion;
    }

    public void setInm_id(String inm_promocion) {
        this.inm_promocion = inm_promocion;
    }

    public int getInm_id() {
        return inm_id;
    }

    public void setInm_id(int inm_id) {
        this.inm_id = inm_id;
    }

    public String getInm_tipologia_promocion() {
        return inm_tipologia_promocion;
    }

    public void setInm_tipologia_promocion(String inm_tipologia_promocion) {
        this.inm_tipologia_promocion = inm_tipologia_promocion;
    }

    public String getInm_tipo_inmueble() {
        return inm_tipo_inmueble;
    }

    public void setInm_tipo_inmueble(String inm_tipo_inmueble) {
        this.inm_tipo_inmueble = inm_tipo_inmueble;
    }

    public String getInm_tipo_producto() {
        return inm_tipo_producto;
    }

    public void setInm_tipo_producto(String inm_tipo_producto) {
        this.inm_tipo_producto = inm_tipo_producto;
    }

    public String getInm_adaptado_circular_bde() {
        return inm_adaptado_circular_bde;
    }

    public void setInm_adaptado_circular_bde(String inm_adaptado_circular_bde) {
        this.inm_adaptado_circular_bde = inm_adaptado_circular_bde;
    }

    public String getInm_descripcion() {
        return inm_descripcion;
    }

    public void setInm_descripcion(String inm_descripcion) {
        this.inm_descripcion = inm_descripcion;
    }

    public String getInm_municipio_ine() {
        return inm_municipio_ine;
    }

    public void setInm_municipio_ine(String inm_municipio_ine) {
        this.inm_municipio_ine = inm_municipio_ine;
    }

    public int getInm_cp() {
        return inm_cp;
    }

    public void setInm_cp(int inm_cp) {
        this.inm_cp = inm_cp;
    }

    public String getInm_provincia_prinex() {
        return inm_provincia_prinex;
    }

    public void setInm_provincia_prinex(String inm_provincia_prinex) {
        this.inm_provincia_prinex = inm_provincia_prinex;
    }

    public String getInm_ccaa() {
        return inm_ccaa;
    }

    public void setInm_ccaa(String inm_ccaa) {
        this.inm_ccaa = inm_ccaa;
    }

    public String getInm_latitud_longitud() {
        return inm_latitud_longitud;
    }

    public void setInm_latitud_longitud(String inm_latitud_longitud) {
        this.inm_latitud_longitud = inm_latitud_longitud;
    }

    public String getInm_referencia_catastro() {
        return inm_referencia_catastro;
    }

    public void setInm_referencia_catastro(String inm_referencia_catastro) {
        this.inm_referencia_catastro = inm_referencia_catastro;
    }

    public float getInm_m2_construidos() {
        return inm_m2_construidos;
    }

    public void setInm_m2_construidos(float inm_m2_construidos) {
        this.inm_m2_construidos = inm_m2_construidos;
    }

    public String getInm_perimetro_junio_15() {
        return inm_perimetro_junio_15;
    }

    public void setInm_perimetro_junio_15(String inm_perimetro_junio_15) {
        this.inm_perimetro_junio_15 = inm_perimetro_junio_15;
    }

    public String getInm_latitud() {
        return inm_latitud;
    }

    public String getInm_longitud() {
        return inm_longitud;
    }

}
