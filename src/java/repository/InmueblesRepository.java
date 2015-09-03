package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBConnection;
import model.Inmueble;

public class InmueblesRepository {

    private DBConnection DBConn = new DBConnection();
    private ArrayList<Inmueble> inmuebles;
    private ArrayList<String> provincies;
    private ArrayList<String> localidades;
    private ArrayList<String> tiposInmueble;
    private ArrayList<String> promociones;
    private Inmueble property;

    public InmueblesRepository() {
        this.provincies = new ArrayList<>();
        this.localidades = new ArrayList<>();
        this.promociones = new ArrayList<>();
        this.tiposInmueble = new ArrayList<>();
        this.inmuebles = new ArrayList();
        this.provincies = setProvincias();
    }

    public DBConnection getDBConn() {
        return DBConn;
    }

    public void setDBConn(DBConnection DBConn) {
        this.DBConn = DBConn;
    }

    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(ArrayList<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    public ArrayList<String> getTiposInmueble() {
        return tiposInmueble;
    }

    public void setTiposInmueble(ArrayList<String> tiposInmueble) {
        this.tiposInmueble = tiposInmueble;
    }

    public ArrayList<String> getPromociones() {
        return promociones;
    }

    public void setPromociones(ArrayList<String> promociones) {
        this.promociones = promociones;
    }

    public ArrayList<String> setProvincias() {
        return getProvincies();
    }

    public Inmueble getProperty() {
        return property;
    }

    public void setProperty(int id) {
        this.property = getInmueble(id);
    }

    public void setPropertyEmpty() {
        this.property = new Inmueble();
    }

    public ArrayList<String> getProvincies() {
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement to intialize provincias...");
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = DBConn.getConnection().createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String sql = "SELECT DISTINCT inm_provincia_prinex FROM inmuebles ORDER BY inm_provincia_prinex ASC";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            provincies.add("");
            while (rs.next()) {
                String provincia = rs.getString("inm_provincia_prinex");
                provincies.add(provincia);
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConn.closeConnection();
            DBConn.setConnNull();
        }
        return provincies;
    }

    public ArrayList<String> getCities(String provincia) {
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement to get localidades...");
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = DBConn.getConnection().createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String sql = "SELECT DISTINCT inm_municipio_ine FROM inmuebles WHERE inm_provincia_prinex= \"" + provincia + "\" ORDER BY inm_municipio_ine ASC";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            localidades.clear();
            while (rs.next()) {
                String localidad = rs.getString("inm_municipio_ine");
                localidades.add(localidad);

            }

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConn.closeConnection();
            DBConn.setConnNull();
        }
        return localidades;
    }

    public ArrayList<String> getPromos(String provincia, String localidad) {
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement to get promos...");
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = DBConn.getConnection().createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String sql = "SELECT DISTINCT inm_promocion FROM inmuebles WHERE inm_provincia_prinex= \"" + provincia + "\" AND inm_municipio_ine= \"" + localidad + "\" ORDER BY inm_promocion ASC";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            promociones.clear();
            while (rs.next()) {
                String promo = rs.getString("inm_promocion");
                promociones.add(promo);

            }

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConn.closeConnection();
            DBConn.setConnNull();
        }
        return promociones;
    }

    public ArrayList<String> getTiposInmueble(String promocion, String localidad, String provincia) {
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement to get inmu tipo...");
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {

                String sql = "SELECT DISTINCT inm_tipo_inmueble FROM inmuebles WHERE inm_promocion= ? "
                        + " AND inm_municipio_ine = ?"
                        + " AND  inm_provincia_prinex=?"
                        + " ORDER BY inm_tipo_inmueble ASC";
                System.out.println(sql);

                System.out.println("promocion:" + promocion);
                System.out.println("localidad:" + localidad);
                System.out.println("provincia:" + provincia);

                stmt = DBConn.getConnection().prepareStatement(sql);
                stmt.setString(1, promocion);
                stmt.setString(2, localidad);
                stmt.setString(3, provincia);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            rs = stmt.executeQuery();
            tiposInmueble.clear();
            while (rs.next()) {
                String tipo = rs.getString("inm_tipo_inmueble");
                tiposInmueble.add(tipo);
            }

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConn.closeConnection();
            DBConn.setConnNull();
        }
        return tiposInmueble;
    }

    public void selectTop(int top, String table) {
        if (DBConn.getConnection() != null) {
            try {
                String topQuery = "";
                if (top > 0) {
                    topQuery = " TOP " + top;
                }
                PreparedStatement prepStatement = DBConn.getConnection().prepareStatement("SELECT" + topQuery + " FROM " + table);
                ResultSet result = prepStatement.executeQuery();
                if (result != null) {
                    while (result.next()) {

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConn.closeConnection();
                DBConn.setConnNull();
            }
        }
    }

    public ArrayList<Inmueble> getInmuebles(String provincia, String localidad, String promocion, String tipo_inmueble, boolean consultaMapa) {
        if (DBConn.getConnection() != null) {
            try {
                System.out.println("promocion:" + promocion);
                System.out.println("localidad:" + localidad);
                System.out.println("tipo_inmueble:" + tipo_inmueble);
                System.out.println("provincia:" + provincia);
                //STEP 4: Execute a query
                System.out.println("Creating statement to get properties...");
                PreparedStatement stmt = null;
                ResultSet rs = null;
                int index = 2;
                try {

                    String sql = "SELECT * FROM inmuebles WHERE inm_provincia_prinex=?";
                    if (localidad != null) {
                        if (!localidad.equals("")) {
                            sql += " AND inm_municipio_ine =? ";
                        }
                    }
                    if (promocion != null) {
                        if (!promocion.equals("")) {
                            sql += " AND inm_promocion =? ";
                        }
                    }
                    if (tipo_inmueble != null) {
                        if (!tipo_inmueble.equals("")) {
                            sql += " AND inm_tipo_inmueble =? ";
                        }
                    }
                    //If drawing the map, we dont want the properties with no valid coordinates
                    if (consultaMapa) {
                        sql += " AND inm_latitud_longitud NOT IN ('','-',' ')";
                    }

                    sql += " ORDER BY inm_municipio_ine asc, inm_tipo_inmueble asc,inm_m2_construidos asc";

                    System.out.println(sql);
                    System.out.println("promocion:" + promocion);
                    System.out.println("localidad:" + localidad);
                    System.out.println("tipo_inmueble:" + tipo_inmueble);
                    System.out.println("provincia:" + provincia);
                    stmt = DBConn.getConnection().prepareStatement(sql);
                    stmt.setString(1, provincia);

                    if (localidad != null) {
                        if (!localidad.equals("")) {
                            stmt.setString(index, localidad);
                            index++;
                        }
                    }
                    if (promocion != null) {
                        if (!promocion.equals("")) {
                            stmt.setString(index, promocion);
                            index++;
                        }
                    }
                    if (tipo_inmueble != null) {
                        if (!tipo_inmueble.equals("")) {
                            stmt.setString(index, tipo_inmueble);
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                rs = stmt.executeQuery();
                inmuebles.clear();
                while (rs.next()) {
                    Inmueble property = new Inmueble(rs.getInt("inm_id"),
                            rs.getString("inm_promocion"),
                            rs.getString("inm_tipologia_promocion"),
                            rs.getString("inm_tipo_inmueble"),
                            rs.getString("inm_tipo_producto"),
                            rs.getString("inm_adaptado_circular_bde"),
                            rs.getString("inm_descripcion"),
                            rs.getString("inm_municipio_ine"),
                            rs.getInt("inm_cp"),
                            rs.getString("inm_provincia_prinex"),
                            rs.getString("inm_ccaa"),
                            rs.getString("inm_latitud_longitud"),
                            rs.getString("inm_referencia_catastro"),
                            rs.getFloat("inm_m2_construidos"),
                            rs.getString("inm_perimetro_junio_15"));

                    inmuebles.add(property);
                }
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DBConn.closeConnection();
                DBConn.setConnNull();
            }
        }

        return inmuebles;
    }

    public Inmueble getInmueble(int id) {
        property = null;
        if (DBConn.getConnection() != null) {
            try {

                System.out.println("Creating statement to get 1 property with id..." + id);
                PreparedStatement stmt = null;
                ResultSet rs;
                try {
                    String sql = "SELECT * FROM inmuebles WHERE inm_id=?";
                    stmt = DBConn.getConnection().prepareStatement(sql);
                    stmt.setInt(1, id);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                rs = stmt.executeQuery();
                while (rs.next()) {
                    property = new Inmueble(rs.getInt("inm_id"),
                            rs.getString("inm_promocion"),
                            rs.getString("inm_tipologia_promocion"),
                            rs.getString("inm_tipo_inmueble"),
                            rs.getString("inm_tipo_producto"),
                            rs.getString("inm_adaptado_circular_bde"),
                            rs.getString("inm_descripcion"),
                            rs.getString("inm_municipio_ine"),
                            rs.getInt("inm_cp"),
                            rs.getString("inm_provincia_prinex"),
                            rs.getString("inm_ccaa"),
                            rs.getString("inm_latitud_longitud"),
                            rs.getString("inm_referencia_catastro"),
                            rs.getFloat("inm_m2_construidos"),
                            rs.getString("inm_perimetro_junio_15"));
                }

                //System.out.println("property.getInm_municipio_ine()" + property.getInm_municipio_ine());
                //System.out.println("getInm_referencia_catastro()" + property.getInm_referencia_catastro());
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DBConn.closeConnection();
                DBConn.setConnNull();
            }
        }

        return property;
    }

    public int actualizaInmueble(Inmueble updatedProperty) {
        int actualizado = 0;
        int insertado = 0;
        if (DBConn.getConnection() != null) {
            try {

                System.out.println("Creating statement save into the updates table the old data,property with id..." + updatedProperty.getInm_id());
                Statement stmt = null;
                try {

                    String sql = "INSERT INTO inmuebles_modificados"
                            + " SELECT 0,i.*,NOW() FROM inmuebles i"
                            + " WHERE inm_id=" + updatedProperty.getInm_id();
                    stmt = DBConn.getConnection().createStatement();
                    System.out.println("INSERT SQL FOR UPDATES TABLE:" + sql);
                    insertado = stmt.executeUpdate(sql);
                    System.out.println("INSERTADO:" + insertado);

                    if (insertado == 1) {
                        sql = "UPDATE inmuebles SET"
                                + " inm_tipologia_promocion=?,"
                                + "inm_tipo_inmueble=?,"
                                + "inm_tipo_producto=?,"
                                + "inm_adaptado_circular_bde=?,"
                                + "inm_descripcion=?,"
                                + "inm_cp=?,"
                                + "inm_provincia_prinex=?,"
                                + "inm_ccaa=?,"
                                + "inm_latitud_longitud=?,"
                                + "inm_referencia_catastro=?,"
                                + "inm_m2_construidos=?,"
                                + "inm_perimetro_junio_15=?,"
                                + "inm_promocion=?"
                                + " WHERE inm_id=?";
                        PreparedStatement pstmt = DBConn.getConnection().prepareStatement(sql);
                        pstmt.setString(1, updatedProperty.getInm_tipologia_promocion());
                        pstmt.setString(2, updatedProperty.getInm_tipo_inmueble());
                        pstmt.setString(3, updatedProperty.getInm_tipo_producto());
                        pstmt.setString(4, updatedProperty.getInm_adaptado_circular_bde());
                        pstmt.setString(5, updatedProperty.getInm_descripcion());
                        pstmt.setInt(6, updatedProperty.getInm_cp());
                        pstmt.setString(7, updatedProperty.getInm_provincia_prinex());
                        pstmt.setString(8, updatedProperty.getInm_ccaa());
                        pstmt.setString(9, updatedProperty.getInm_latitud_longitud());
                        pstmt.setString(10, updatedProperty.getInm_referencia_catastro());
                        pstmt.setFloat(11, updatedProperty.getInm_m2_construidos());
                        pstmt.setString(12, updatedProperty.getInm_perimetro_junio_15());
                        pstmt.setString(13, updatedProperty.getInm_promocion());
                        pstmt.setInt(14, updatedProperty.getInm_id());

                        System.out.println("UPDATED SQL STATEMENT:" + pstmt);
                        try {
                            actualizado = pstmt.executeUpdate();
                            System.out.println("ACTUALIZADO:" + actualizado);
                        } catch (Exception e) {
                            e.printStackTrace();
                            PreparedStatement pstmt2 = null;
                            ResultSet rs;
                            try {

                                String sql2 = "SELECT MAX(inm_index) as max FROM inmuebles_modificados WHERE inm_id=?";
                                pstmt = null;
                                pstmt = DBConn.getConnection().prepareStatement(sql2);
                                pstmt.setInt(1, updatedProperty.getInm_id());
                                System.out.println(pstmt);

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            rs = pstmt.executeQuery();
                            while (rs.next()) {
                                int index = rs.getInt("max");
                                stmt = DBConn.getConnection().createStatement();
                                //if we cant update the property properly we delete the last insert we made on the
                                System.out.println("THERE WAS AN ERROR UPDATING,DELETING INSERTED DATA");
                                sql = "DELETE FROM inmuebles_modificados"
                                        + " WHERE inm_index =" + index;
                                stmt = DBConn.getConnection().createStatement();
                                System.out.println("DELETE THE PREVIOUS INSERT :" + sql);
                                stmt.executeUpdate(sql);
                            }

                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //System.out.println("property.getInm_municipio_ine()" + property.getInm_municipio_ine());
                //System.out.println("getInm_referencia_catastro()" + property.getInm_referencia_catastro());
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DBConn.closeConnection();
                DBConn.setConnNull();
            }
        }

        return actualizado + insertado;
    }

}
