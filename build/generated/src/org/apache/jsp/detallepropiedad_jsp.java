package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import repository.InmueblesRepository;

public final class detallepropiedad_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/headIncludes.html", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <title>Ver en detalle</title>\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("            function initMap()\n");
      out.write("            {\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");
 InmueblesRepository repo = new InmueblesRepository();
      out.write("\n");
      out.write("        <div class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("\n");
      out.write("            </div> \n");
      out.write("            <div class=\"navbar-collapse collapse\">\n");
      out.write("                <a class=\"navbar-brand\" href=\"index.jsp\"><i class=\"fa fa-list-alt\"></i> Listado</a>\n");
      out.write("                <a class=\"navbar-brand\" href=\"#\"><i class=\"fa fa-search\"></i>Búsqueda por referencia</a>\n");
      out.write("                <a class=\"navbar-brand\" href=\"#\"><i class=\"fa fa-search-plus\"></i>Búsqueda Libre</a>\n");
      out.write("                <a class=\"navbar-brand\" href=\"#\"><i class=\"fa fa-map-o\"></i> Ver Mapa</a>\n");
      out.write("\n");
      out.write("            </div><!--/.nav-collapse -->\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"container margin-top-40\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <form role=\"form\">\n");
      out.write("                    <div class=\"col-lg-6\">\n");
      out.write("                        <div class=\"well well-sm\"><strong><span class=\"glyphicon glyphicon-asterisk\"></span>Required Field</strong></div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"InputName\">Enter Name</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <input type=\"text\" class=\"form-control\" name=\"InputName\" id=\"InputName\" placeholder=\"Enter Name\" required>\n");
      out.write("                                <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"InputEmail\">Enter Email</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <input type=\"email\" class=\"form-control\" id=\"InputEmailFirst\" name=\"InputEmail\" placeholder=\"Enter Email\" required>\n");
      out.write("                                <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"InputEmail\">Confirm Email</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <input type=\"email\" class=\"form-control\" id=\"InputEmailSecond\" name=\"InputEmail\" placeholder=\"Confirm Email\" required>\n");
      out.write("                                <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"InputMessage\">Enter Message</label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <textarea name=\"InputMessage\" id=\"InputMessage\" class=\"form-control\" rows=\"5\" required></textarea>\n");
      out.write("                                <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-asterisk\"></span></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Submit\" class=\"btn btn-info pull-right\">\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                <div class=\"col-lg-5 col-md-push-1\">\n");
      out.write("                    <div class=\"col-md-12\">\n");
      out.write("                        <div class=\"alert alert-success\">\n");
      out.write("                            <strong><span class=\"glyphicon glyphicon-ok\"></span> Success! Message sent.</strong>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"alert alert-danger\">\n");
      out.write("                            <span class=\"glyphicon glyphicon-remove\"></span><strong> Error! Please check all page inputs.</strong>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
