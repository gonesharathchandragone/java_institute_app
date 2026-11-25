package com.sharath.devops.servlet;

import com.sharath.devops.dao.CourseDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        try {
            req.setAttribute("courses", courseDAO.findAll());
        } catch (SQLException e) {
            throw new ServletException("Could not load courses", e);
        }
        req.getRequestDispatcher("/WEB-INF/views/courses.jsp").forward(req, resp);
    }
}
