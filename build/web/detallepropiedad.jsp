<%@page import="model.Inmueble"%>
<%@page import="repository.InmueblesRepository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% InmueblesRepository repo = new InmueblesRepository();
        Inmueble property = new Inmueble();
        int id = Integer.parseInt("0" + request.getParameter("id"));
        if (id > 0) {
            repo.getInmueble(id);
            property = repo.getProperty();

        }
    %>
    <head>

        <%@include file="includes/headIncludes.html" %>



        <title>Ver en detalle</title>

        <script type="text/javascript">

            function actualizarDatos()
            {
                var camposObligatoriosTexto = ['promocion', 'tipoInmueble', 'provinciaPrinex', 'localidad', 'descripcion'];
                var hayError = false;
                for (var i = 0; i < camposObligatoriosTexto.length - 1; i++) {
                    var id = "#" + camposObligatoriosTexto[i];
                    var div = "#d" + camposObligatoriosTexto[i];
                    if ($(id).val().trim().length <= 0)
                    {
                        hayError = true;
                        $(div).addClass("has-error");
                    }
                    else {
                        $(div).removeClass("has-error");
                    }
                }
                if (hayError) {
                    $("#divError").removeClass("hidden");
                    return false;
                }


                var url = "ActualizaInmuebleServlet"; // the script where you handle the form input.
                $.ajax({
                    type: "POST",
                    url: url,
                    data: $("#formDetalle").serialize(), // serializes the form's elements.
                    success: function (data)
                    {
                        if (parseInt(data) !== 2)
                        {
                            $("#divErrorUpdate").removeClass("hidden");
                            $("#divSuccess").addClass("hidden");
                        }
                        else
                        {
                            $("#divErrorUpdate").addClass("hidden");
                            $("#divSuccess").removeClass("hidden");
                        }
                        return false; // avoid to execute the actual submit of the form.
                    }
                });
                return false;
            }


            function initMap(latitud, longitud)
            {
                $("#mapaDiv").html("<h1><span class='label label-info'>Cargando mapa correspondiente...</span></h1>");

                $("#mapaDiv").empty();
                var map;
                //Not sure how to check for undefined so if the lat and lng are undefined i compare the them to another undefined variable so it enters the if
                if (latitud === undefined && longitud === undefined)
                {
                    var latLng = new google.maps.LatLng(<% out.print(property.getInm_latitud() + "," + property.getInm_longitud());%>);
                }
                else
                {
                    var latLng = new google.maps.LatLng(latitud, longitud);
                }

                map = new google.maps.Map(document.getElementById('mapaDiv'), {
                    zoom: 16,
                    center: latLng,
                    title: 'Inmuebles',
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                });
                var markers = [];
                var marker = new google.maps.Marker({
                    position: latLng,
                    title: "Inmueble en venta"
                });
                markers.push(marker);
                var markerCluster = new MarkerClusterer(map, markers);
            }

            function geoLocalizar()
            {
                var street = $("#descripcion").val();
                var province = $("#provinciaPrinex").val();
                var city = $("#localidad").val();
                address = street + "," + province + "," + city;
                var latitude;
                var longitude;
                var extra = "&components=country:ES&KEY=AIzaSyBsEnx35wmxQdlI7btdxKL_IlCio4UrJKs";
                var url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + extra;
                // alert(url);
                $.getJSON(url, function (json) {
                    if (json.status === "OK")
                    {
                        var address = json.results[0].formatted_address;
                        console.log('Address : ', address);
                        var latitude = json.results[0].geometry.location.lat;
                        console.log('Latitude : ', latitude);
                        $("#latitud").val(latitude);
                        var longitude = json.results[0].geometry.location.lng;
                        console.log('Longitude : ', longitude);
                        $("#longitud").val(longitude);
                        initMap(latitude, longitude);
                        $("#divGeolocalizar").removeClass("hidden");
                        $("#divGeolocalizar").html("La dirección que se utilizo para la geolocalización fué...\n" + address + "<br/>Si no se corresponde con lo esperado por favor no guarde los datos y recargue la página.</strong>");
                        //alert("Se ha geolocalizado lo siguiente:"+address);
                    }
                    else
                    {
                        $("#divGeolocalizar").removeClass("hidden");
                        $("#divGeolocalizar").html("Hubo algún problema al intentar geolocalizar,Status:" + json.status);
                        //alert("Hubo algún problema al intentar geolocalizar,Status:" + json.status);
                    }
                });
            }





        </script>

    </head>

    <body>

        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

            </div> 
            <div class="navbar-collapse collapse">
                <a class="navbar-brand" href="index.jsp"><i class="fa fa-list-alt"></i> Listado</a>
                <a class="navbar-brand" href="#"><i class="fa fa-search"></i>Búsqueda por referencia</a>
                <a class="navbar-brand" href="#"><i class="fa fa-search-plus"></i>Búsqueda Libre</a>
                <a class="navbar-brand" href="#"><i class="fa fa-map-o"></i> Ver Mapa</a>

            </div><!--/.nav-collapse -->
        </div>

        <div class="container margin-top-40">
            <form role="form"  id="formDetalle" name="formDetalle"  action="javascript:void(0);" >
                <div class="row form-group">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading clearfix">
                                <h3 class="panel-title">
                                    <i class="glyphicon glyphicon-folder-open"></i>
                                    <%if (request.getParameter("id") == null) {
                                            out.print("Nueva Propiedad");
                                        } else {
                                            out.print("Modificando propiedad con referencia:" + request.getParameter("id"));
                                        }
                                    %>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-12 form-group" id="dpromocion">
                                            <label for="promocion" class="control-label">Promoción:</label>
                                            <input type="text" class="form-control " name="promocion" id="promocion"  value="<%out.print(property.getInm_promocion());%>" > 
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12 form-group">
                                            <label for="tipologiaPromocion">Tipología Promoción:</label>
                                            <input type="text" class="form-control" name="tipologiaPromocion"  data-error="Este campo no puede estar vacío" id="tipologiaPromocion" value="<% out.print(property.getInm_tipologia_promocion()); %>" > 

                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12" id="dtipoInmueble">
                                            <label for="tipoInmueble">Tipo Inmueble:</label>
                                            <input    type="text" class="form-control" name="tipoInmueble" id="tipoInmueble" value="<% out.print(property.getInm_tipo_inmueble()); %>" > 
                                        </div>


                                    </div>
                                    <div class="row">

                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="ccaa">CCAA:</label>
                                            <input type="text" class="form-control" name="ccaa" id="ccaa" value="<% out.print(property.getInm_ccaa()); %>"> 

                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12" id="dprovinciaPrinex">
                                            <label for="provinciaPrinex">Provincia Prinex:</label>
                                            <input    type="text" class="form-control" name="provinciaPrinex" id="provinciaPrinex" value="<% out.print(property.getInm_provincia_prinex()); %>"> 

                                        </div>

                                        <div class="col-md-3 col-sm-6 col-xs-12" id="dlocalidad">
                                            <label for="localidad">Municipio:</label>
                                            <input     type="text" class="form-control" name="localidad" id="localidad" value="<% out.print(property.getInm_municipio_ine()); %>"> 

                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="cp">C.P:</label>
                                            <input type="text" class="form-control" name="cp" id="cp" value="<% out.print(property.getInm_cp()); %>"> 
                                        </div>


                                    </div>
                                    <div class="row">
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="tipoProducto">Tipo Producto:</label>
                                            <input type="text" class="form-control" name="tipoProducto" id="tipoProducto" value="<% out.print(property.getInm_tipo_producto()); %>">
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="adaptadorbde">Adap. Circu. BDE:</label>
                                            <input type="text" class="form-control" name="adaptadorbde" id="adaptadorbde" value="<% out.print(property.getInm_adaptado_circular_bde()); %>"> 
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="latitud">Latitud:</label>
                                            <input type="text" class="form-control" name="latitud" id="latitud" value="<% out.print(property.getInm_latitud()); %>"> 
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="longitud">Longitud:</label>
                                            <input type="text" class="form-control" name="longitud" id="longitud" value="<% out.print(property.getInm_longitud()); %>"> 
                                        </div>


                                    </div>
                                    <div class="row">
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="refCatastro">Ref. Catastro:</label>
                                            <input type="text" class="form-control" name="refCatastro" id="refCatastro"  value="<% out.print(property.getInm_referencia_catastro()); %>"> 
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="superficieCons">Sup. Construida:</label>
                                            <input type="text" class="form-control" name="superficieCons" id="superficieCons"  value="<% out.print(property.getInm_m2_construidos()); %>"> 
                                        </div>
                                        <div class="col-md-3 col-sm-6 col-xs-12">
                                            <label for="perimetroJunio">Perímetro Junio:</label>
                                            <input type="text" class="form-control" name="perimetroJunio" id="perimetroJunio" value="<% out.print(property.getInm_perimetro_junio_15());%>"> 
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <label for="descripcion">Descripción/Dirección:</label>
                                            <textarea    class="form-control" name="descripcion" id="descripcion"> <% out.print(property.getInm_descripcion());%></textarea >
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <button name="geolocalizar"  id="geolocalizar" class="btn btn-success glyphicon glyphicon-search pull-right margin-top-10  " onCLick="geoLocalizar();
                                                    return false;">Geolocalizar</button>
                                            <button  class="btn btn-info glyphicon glyphicon-hand-up pull-right margin-top-10 margin-right-20" onClick="actualizarDatos();" >Actualizar</button>
                                        </div>
                                    </div>
                                    <div class="row margin-top-20">

                                        <div class="col-md-12">
                                            <div class="alert alert-info hidden" id="divGeolocalizar">

                                            </div>
                                            <div class="alert alert-success hidden" id="divSuccess">
                                                <strong><span class="glyphicon glyphicon-ok"></span>Inmueble actualizado correctamente,si ha introducido las coordenadas manualmente
                                                    ,por favor vuelva a cargar la página para ver el nuevo mapa.</strong>
                                            </div>

                                            <div class="alert alert-danger hidden" id="divError">
                                                <span class="glyphicon glyphicon-remove"></span><strong>Alguno de los campos obligatorios no está cumplimentado correctamente.</strong>
                                            </div>

                                            <div class="alert alert-danger hidden" id="divErrorUpdate">
                                                <span class="glyphicon glyphicon-remove"></span><strong>Hubo algún error al actualizar el inmueble,por favor compruebe los datos.</strong>
                                            </div>
                                        </div>
                                        <input hidden type="text"  name="id" id="id" value="<% out.print(property.getInm_id());%>"> 
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row map-size-ficha margin-top-20" >
                <label for="mapaDiv">Localización:</label>
                <div id="mapaDiv"  class="map-size-ficha" ></div>
            </div>
        </div>

    </body>
</html>