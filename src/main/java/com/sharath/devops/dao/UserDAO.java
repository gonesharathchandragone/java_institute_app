package com.sharath.devops.dao;

import com.sharath.devops.model.User;
import com.sharath.devops.util.DBUtil;
import com.sharath.devops.util.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /** Returns true if created, false if the email is already registered. */
    public boolean createUser(String fullName, String email, String password) throws SQLException {
        String sql = "INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email.toLowerCase());
            ps.setString(3, PasswordUtil.hash(password));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // duplicate entry
                return false;
            }
            throw e;
        }
    }

    /** Returns the user if credentials are valid, otherwise null. */
    public User authenticate(String email, String password) throws SQLException {
        String sql = "SELECT id, full_name, email, password_hash FROM users WHERE email = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verify(password, rs.getString("password_hash"))) {
                    return new User(rs.getInt("id"), rs.getString("full_name"), rs.getString("email"));
                }
            }
        }
        return null;
    }
}
