package com.mycompany.firstjsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloWorld", urlPatterns = { "/helloworld" }, initParams = {
        @WebInitParam(name = "name", value = "andy") })
// Annotation will be override by web.xml
// if the same servlet or init-params have been sat.

public class HelloWorld extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello! World!</title>");
        out.println("<body>");
        out.println("<h1>Hello! World!</h1>");
        out.println("<form method=\"post\">");
        out.println("<input name=\"name\">");
        out.println("<button type=\"submit\">send</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String secretName = getInitParameter("name");
        if (name.equals(secretName)) {
            request.getRequestDispatcher("/WEB-INF/Secret.html").forward(request, response);
            // request.getRequestDispatcher("WEB-INF/Secret.html").forward(request,
            // response);
            // request.getRequestDispatcher("/test.jsp").forward(request, response); // work
            // good
            // request.getRequestDispatcher("test.jsp").forward(request, response); // work
            // good
            // response.sendRedirect("/jsp-lab/WEB-INF/Secret.html");
            // response.sendRedirect("WEB-INF/Secret.html");
            return;
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello: " + name);
        out.println("<br>");
        out.println(getServletContext().getInitParameter("message"));
    }
}
