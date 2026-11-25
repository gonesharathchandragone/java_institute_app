package com.sharath.devops.servlet;

import com.sharath.devops.dao.UserDAO;
import com.sharath.devops.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login", ""})
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s != null && s.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/courses");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = trim(req.getParameter("email"));
        String password = req.getParameter("password");

        try {
            User user = (email.isEmpty() || password == null) ? null : userDAO.authenticate(email, password);
            if (user == null) {
                req.setAttribute("error", "Invalid email or password.");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                return;
            }
            HttpSession old = req.getSession(false);
            if (old != null) old.invalidate(); // prevent session fixation
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/courses");
        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }

    private static String trim(String s) { return s == null ? "" : s.trim(); }
}
