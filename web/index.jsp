<%@page import="repository.InmueblesRepository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="includes/headIncludes.html"/>
        <title>Listado</title>



    </head>

    <body>
        <% InmueblesRepository repo = new InmueblesRepository(); %>
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
                <a class="navbar-brand" href="index.jsp"><i class="fa fa-list-alt"></i>Listado</a>
                <a class="navbar-brand" href="#"><i class="fa fa-search"></i>Búsqueda por referencia</a>
                <a class="navbar-brand" href="#"><i class="fa fa-search-plus"></i>Búsqueda Libre</a>
                <a class="navbar-brand" href="#"><i class="fa fa-map-o"></i> Ver Mapa</a>

            </div><!--/.nav-collapse -->
        </div>
        <div class="container margin-top-40">


            <form role="form" method="post" id="formFilter" name="formFilter" action="javascript:void(0);">
                <div class="row form-group">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading clearfix">
                                <i class="icon-calendar"></i>
                                <h3 class="panel-title">Búsqueda</h3>
                            </div>

                            <div class="panel-body">
                                <div class=" col-md-3 col-sm-6 col-xs-12 ">
                                    <label for="provincia" >Provincia</label>

                                    <select id="provincia" name="provincia" class="form-control" onChange="ajaxLocalidades(this.value);">
                                        <%for (String provincia : repo.getProvincies()) {%>
                                        <option value="<%out.print(provincia);%>"><%out.print(provincia);%></option>
                                        <%}%>
                                    </select>
                                </div>
                                <div class=" col-md-3 col-sm-6 col-xs-12">
                                    <label for="localidad">Localidad</label>
                                    <select class="form-control" id="localidad" name="localidad" onChange="ajaxPromociones(this.value);">

                                    </select>
                                </div>

                                <div class="col-md-3 col-sm-6 col-xs-12">
                                    <label for="promocion">Promocion</label>
                                    <select class="form-control" name="promocion" id="promocion" onChange="ajaxTiposInmueble(this.value);">
                                        <option value=""></option>
                                    </select>
                                </div>
                                <div class=" col-md-3 col-sm-6 col-xs-12">
                                    <label for="tipo_inmueble">Tipo de inmueble</label>
                                    <select class="form-control" name="tipo_inmueble" id="tipo_inmueble">
                                        <option value=""></option>
                                    </select>
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12 ">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <button name="mostrar"  class="btn btn-success glyphicon glyphicon-hand-up pull-right margin-top-10" onclick="sendForm();">BUSCAR</button>
                                        <button name="limpiar"  class="btn btn-warning glyphicon glyphicon-trash pull-right margin-top-10  margin-right-20" onclick="cleanForm();">LIMPIAR</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="row" >
                <div id="tablaInmueblesDiv" class="col-md-12 col-sm-12 col-xs-12"></div>
            </div>
            <div class="row height-map margin-top-20" >
                <div id="mapaDiv"  class="height-map" ></div>
            </div>


        </div>
        <script type="text/javascript">
            function ajaxLocalidades(provincia)
            {

                $("#select2-chosen-2").html("Cargando localidades...");
                $.ajax({
                    type: 'POST',
                    url: "/RealStateManager/GetLocalidadesServlet",
                    data: "provincia=" + provincia,
                    dataType: 'html',
                    success: function (data) {
                        $("#localidad").empty();
                        $("#localidad").html(data);
                        $('#localidad option')[0].selected = true;
                        $("#select2-chosen-2").html($('#localidad option:selected').val());
                        $('#promocion option')[0].selected = true;
                        $("#select2-chosen-3").html($('#promocion option:selected').val());
                        $('#tipo_inmueble option')[0].selected = true;
                        $("#select2-chosen-4").html($('#tipo_inmueble option:selected').val());
                    }
                });
            }
            function ajaxPromociones(localidad)
            {
                $("#select2-chosen-3").html("Cargando promociones...");
                $.ajax({
                    type: 'POST',
                    url: "/RealStateManager/GetPromocionesServlet",
                    data: "provincia=" + $('#provincia').val() + "&localidad=" + localidad,
                    dataType: 'html',
                    success: function (data) {
                        $("#promocion").empty();
                        $("#promocion").html(data);
                        $('#promocion option')[0].selected = true;
                        $("#select2-chosen-3").html($('#promocion option:selected').val());
                        $('#tipo_inmueble option')[0].selected = true;
                        $("#select2-chosen-4").html($('#tipo_inmueble option:selected').val());
                    }
                });
            }



            function ajaxTiposInmueble(promocion)
            {
                $("#select2-chosen-4").html("Cargando tipos de inmueble...");
                $.ajax({
                    type: 'POST',
                    url: "/RealStateManager/GetTiposInmuebleServlet",
                    data: "provincia=" + $('#provincia').val() + "&localidad=" + $('#localidad').val() + "&promocion=" + promocion,
                    dataType: 'html',
                    success: function (data) {
                        $("#tipo_inmueble").empty();
                        $("#tipo_inmueble").html(data);
                        $('#tipo_inmueble option')[0].selected = true;
                        $("#select2-chosen-4").html($('#tipo_inmueble option:selected').val());
                    }
                });
            }



            function sendForm()
            {

                provincia = $('#provincia').val();
                if (provincia === "" || provincia === null)
                {
                    alert("Por favor seleccione por lo menos una provincia para realizar la búsqueda...");
                    return false;
                }

                $("#tablaInmueblesDiv").html("<h1><span class='label label-info'>Cargando el listado de inmuebles,espere un momento...</span></h1>");
                var url = "GetInmueblesServlet"; // the script where you handle the form input.
                $.ajax({
                    type: "POST",
                    url: url,
                    data: $("#formFilter").serialize(), // serializes the form's elements.
                    success: function (data)
                    {
                        $("#tablaInmueblesDiv").empty();
                        $("#tablaInmueblesDiv").html(data);
                        $('#tablaInmuebles').DataTable();
                        initialize();
                        return false; // avoid to execute the actual submit of the form.
                    }
                });
                return false; // avoid to execute the actual submit of the form.
            }
            function initMap()
            {
                return;
            }
            function initialize()
            {

                $("#mapaDiv").html("<h1><span class='label label-info'>Cargando mapa correspondiente...</span></h1>");
                $.ajax({
                    type: "POST",
                    url: "GenerarMapaServlet",
                    data: $("#formFilter").serialize(), // serializes the form's elements.
                    success: function (data)
                    {
                        if (data === "")
                        {
                            $("#mapaDiv").html("<h1><span class='label label-warning'>Ninguno de los registros tiene coordenadas correctas para generar un mapa.</span></h1>");
                        }
                        else
                        {
                            $("#mapaDiv").empty();
                            //data is a string with all the information of the properties
                            //each property is separated by || and each attribute by ##
                            var map;
                            var markers = [];
                            var inmuebles = [];
                            inmuebles = data.split("||"); //get each property with its fields,the 
                            for (var i = 0; i < inmuebles.length - 1; i++) {
                                //Now we separate the attributes into a single property
                                //the attributes are: id,coordinates (lat long),m2 built
                                var inmueble = [];
                                inmueble = inmuebles[i].split("##");
                                //coordinates are separated by a space
                                var coorLatLng = [];
                                coorLatLng = inmueble[1].split(" ");
                                var latLng;
                                if (i === 0)
                                {
                                    //set the center to the first property
                                    var center = new google.maps.LatLng(parseFloat(coorLatLng[0]), parseFloat(coorLatLng[1]));
                                    latLng = new google.maps.LatLng(parseFloat(coorLatLng[0]), parseFloat(coorLatLng[1]));
                                    map = new google.maps.Map(document.getElementById('mapaDiv'), {
                                        zoom: 10,
                                        center: center,
                                        title: 'Inmuebles',
                                        mapTypeId: google.maps.MapTypeId.ROADMAP
                                    });
                                }
                                latLng = new google.maps.LatLng(parseFloat(coorLatLng[0]), parseFloat(coorLatLng[1]));

                                var marker = new google.maps.Marker({
                                    position: latLng,
                                    title: "Ref Inm: " + inmueble[0]
                                            + "\nSuperficie: " + inmueble[2]
                                            + " m2\nTipo inm: " + inmueble[4]
                                            + "\nPromoción: " + inmueble[3]
                                });
                                markers.push(marker);

                            }
                            var markerCluster = new MarkerClusterer(map, markers);
                        }
                    }
                });
            }

            function cleanForm()
            {
                $('#provincia option')[0].selected = true;
                $("#select2-chosen-1").html($('#provincia option:selected').val());
                $("#localidad").empty();
                $("#localidad").html("<option value=''></option>");
                $("#select2-chosen-2").html("");
                $("#promocion").empty();
                $("#promocion").html("<option value=''></option>");
                $("#select2-chosen-3").html("");
                $("#tipo_inmueble").empty();
                $("#tipo_inmueble").html("<option value=''></option>");
                $("#select2-chosen-4").html("");
            }
            $(document).ready(function () {
                $('select').select2();
            });</script>
    </body>
</html>