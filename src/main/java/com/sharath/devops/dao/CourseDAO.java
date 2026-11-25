package com.sharath.devops.dao;

import com.sharath.devops.model.Course;
import com.sharath.devops.util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> findAll() throws SQLException {
        List<Course> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, title, description, duration FROM courses ORDER BY id")) {
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("title"),
                        rs.getString("description"), rs.getString("duration")));
            }
        }
        return list;
    }
}
