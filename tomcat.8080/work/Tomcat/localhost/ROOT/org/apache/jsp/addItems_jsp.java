/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.45
 * Generated at: 2021-05-25 14:41:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import uk.ac.ucl.model.Item;
import java.io.File;

public final class addItems_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/header.jsp", Long.valueOf(1621273756252L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("uk.ac.ucl.model.Item");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.io.File");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    ");
      out.write("<title>Generic List Application</title>\n");
      out.write("<div style=\"text-align: center;padding-bottom:7%\">\n");
      out.write("  <h1><a class=\"HomePageButton\" href=\"index.html\">Generic List Application</a></h1>\n");
      out.write("</div>");
      out.write("\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("styles.css\" />\n");
      out.write("</head>\n");
      out.write("<script>\n");
      out.write("    function updateInputField() {\n");
      out.write("        let x = document.getElementById(\"inputTypes\").value;\n");
      out.write("        let y = \"\";\n");
      out.write("        if (x===\"listType\")\n");
      out.write("        {\n");
      out.write("            x = \"text\";\n");
      out.write("        }\n");
      out.write("        if (x===\"image\")\n");
      out.write("        {\n");
      out.write("            x = \"file\";\n");
      out.write("            y = \"accept='image/*'\";\n");
      out.write("        }\n");
      out.write("        document.getElementById(\"ChooseItem\").innerHTML =\n");
      out.write("            '<br>' +\n");
      out.write("            '<label for=\"value\"><b>Value Of Item<b></label>' +\n");
      out.write("            '<br>' +\n");
      out.write("            '<br>' +\n");
      out.write("            '<input name=\"value\" id=\"value\" type=\"'+x+'\" placeholder=\"Enter Value\" '+y+'>' +\n");
      out.write("            '<br>';\n");
      out.write("\n");
      out.write("    }\n");
      out.write("</script>\n");

    session.setAttribute("previousPage", File.separator+"addItems.jsp");
    String listName = (String) request.getAttribute("listName");
    List<Item> list = (List<Item>) request.getAttribute("list");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage==null) errorMessage = "";
    int listSize;
    try
    {
        listSize = list.size();
    }
    catch (NullPointerException e)
    {
        listSize = 0;
    }

      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"Title\" style=\"text-align: center;\">\n");
      out.write("    <h2>Add Items To ");
      out.print(listName);
      out.write("</h2>\n");
      out.write("</div>\n");
      out.write("<div class=\"ErrorSpace\" style=\"text-align: center;\">\n");
      out.write("    <p style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</p>\n");
      out.write("</div>\n");
      out.write("<div class=\"TableBG\" style=\"text-align: center;width: 100%\">\n");
      out.write("    <div class=\"MainInputTable\" style=\"text-align: center;float: left;width: 65%;\">\n");
      out.write("        <table style=\"width:100%;text-align: center;\">\n");
      out.write("            <tr>\n");
      out.write("                <th>Type</th>\n");
      out.write("                <th>Value</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

                if (listSize>0)
                {
                    for (Item item : list)
                    {
                        String type = item.getType();
                        String value = item.getValue();
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(type);
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(value);
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");

                    }
                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("            ");

                if (listSize<=0)
                {
            
      out.write("\n");
      out.write("            <p>No Items In List</p>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <div class=\"SideInputTable\" style=\"text-align: center;float: left; margin-left: 5%;\">\n");
      out.write("        <div class=\"AddToList\" style=\"text-align: center;float:bottom\">\n");
      out.write("            <form method=\"POST\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/addToList.html\">\n");
      out.write("                <div class=\"ChooseType\" style=\"text-align: center;float:bottom\">\n");
      out.write("                    <label for=\"inputTypes\"><b>Type of Item</b></label>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <select name=\"type\" id=\"inputTypes\" onchange=\"updateInputField()\">\n");
      out.write("                        <option value=\"text\">Text</option>\n");
      out.write("                        <option value=\"url\">URL</option>\n");
      out.write("                        <option value=\"listType\">List</option>\n");
      out.write("                        <option value=\"image\">Image</option>\n");
      out.write("                    </select>\n");
      out.write("                    <br>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ChooseValue\" id=\"ChooseItem\" style=\"text-align: center;float:bottom\">\n");
      out.write("                    <br>\n");
      out.write("                    <label for=\"value\"><b>Value Of Item</b></label>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <input name=\"value\" id=\"value\" type=\"text\" placeholder=\"Enter Value\">\n");
      out.write("                    <br>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"AddItemSubmit\" style=\"text-align: center;float:bottom\">\n");
      out.write("                    <br>\n");
      out.write("                    <input class=\"SubmitButton\" type=\"submit\" value=\"Add Item\"/>\n");
      out.write("                    <br>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
