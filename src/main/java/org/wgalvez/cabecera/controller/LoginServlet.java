package org.wgalvez.cabecera.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.wgalvez.cabecera.service.LoginService;
import org.wgalvez.cabecera.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    static final String USER = "admin";
    static final String PASS = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> usernameOpt = loginService.getUsername(req);
        if (usernameOpt.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Welcome</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hi " + usernameOpt.get() + " you are logged</h1>");
                out.println("<a href='" + req.getContextPath() +"/index.jsp'>Back</a>");
                out.println("<a href='" + req.getContextPath() +"/logout'>Logout</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if (user.equals(USER) && pass.equals(PASS)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", user);
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "user or password incorrect");
        }
    }
}
