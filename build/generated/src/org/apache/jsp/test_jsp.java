package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("          ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/headIncludes.html", out, false);
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       <div class=\"container\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-md-12\">\n");
      out.write("            <div class=\"well well-sm\">\n");
      out.write("                <form class=\"form-horizontal\" method=\"post\">\n");
      out.write("                    <fieldset>\n");
      out.write("                        <legend class=\"text-center header\">Contact us</legend>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <span class=\"col-md-1 col-md-offset-2 text-center\"><i class=\"fa fa-user bigicon\"></i></span>\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <input id=\"fname\" name=\"name\" type=\"text\" placeholder=\"First Name\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <span class=\"col-md-1 col-md-offset-2 text-center\"><i class=\"fa fa-user bigicon\"></i></span>\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <input id=\"lname\" name=\"name\" type=\"text\" placeholder=\"Last Name\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <span class=\"col-md-1 col-md-offset-2 text-center\"><i class=\"fa fa-envelope-o bigicon\"></i></span>\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <input id=\"email\" name=\"email\" type=\"text\" placeholder=\"Email Address\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <span class=\"col-md-1 col-md-offset-2 text-center\"><i class=\"fa fa-phone-square bigicon\"></i></span>\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <input id=\"phone\" name=\"phone\" type=\"text\" placeholder=\"Phone\" class=\"form-control\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <span class=\"col-md-1 col-md-offset-2 text-center\"><i class=\"fa fa-pencil-square-o bigicon\"></i></span>\n");
      out.write("                            <div class=\"col-md-8\">\n");
      out.write("                                <textarea class=\"form-control\" id=\"message\" name=\"message\" placeholder=\"Enter your massage for us here. We will get back to you within 2 business days.\" rows=\"7\"></textarea>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"col-md-12 text-center\">\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-primary btn-lg\">Submit</button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </fieldset>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("    .header {\n");
      out.write("        color: #36A0FF;\n");
      out.write("        font-size: 27px;\n");
      out.write("        padding: 10px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .bigicon {\n");
      out.write("        font-size: 35px;\n");
      out.write("        color: #36A0FF;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
