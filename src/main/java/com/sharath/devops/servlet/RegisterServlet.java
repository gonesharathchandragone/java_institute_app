package com.sharath.devops.servlet;

import com.sharath.devops.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = trim(req.getParameter("fullName"));
        String email = trim(req.getParameter("email"));
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirmPassword");

        String error = null;
        if (name.isEmpty() || name.length() > 100) {
            error = "Please enter your full name.";
        } else if (!EMAIL.matcher(email).matches() || email.length() > 150) {
            error = "Please enter a valid email address.";
        } else if (password == null || password.length() < 8) {
            error = "Password must be at least 8 characters.";
        } else if (!password.equals(confirm)) {
            error = "Passwords do not match.";
        }

        try {
            if (error == null && !userDAO.createUser(name, email, password)) {
                error = "An account with this email already exists.";
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during registration", e);
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.setAttribute("fullName", name);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        req.getSession(true).setAttribute("flash", "Account created. Please log in.");
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private static String trim(String s) { return s == null ? "" : s.trim(); }
}
