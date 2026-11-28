/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.repository;

/**
 *
 * @author ROG
 */

import indofx.admin.config.DatabaseConnection;
import indofx.admin.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserRepository implements UserRepository {
    private final DatabaseConnection db;
    
    public PostgresUserRepository(DatabaseConnection db) {
        this.db = db;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("role"));
        u.setActive(rs.getBoolean("is_active"));
        u.setCreatedAt(rs.getString("created_at"));
        return u;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM public.users WHERE id = ?";
        try {
            Connection conn = db.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByEmailOrName(String emailOrName) {
        String sql = "SELECT * FROM public.users "
                   + "WHERE LOWER(email) = LOWER(?) OR LOWER(name) = LOWER(?) "
                   + "LIMIT 1";
        try {
            Connection conn = db.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, emailOrName.trim());
                ps.setString(2, emailOrName.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return search("", "Semua");
    }

    @Override
    public List<User> search(String keyword, String roleFilter) {
        List<User> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT * FROM public.users WHERE (LOWER(name) LIKE LOWER(?) OR LOWER(email) LIKE LOWER(?))"
        );

        boolean hasRole = roleFilter != null
                && !roleFilter.isEmpty()
                && !roleFilter.equalsIgnoreCase("Semua");

        if (hasRole) {
            sql.append(" AND role = ?");
        }
        sql.append(" ORDER BY id ASC");

        try {
            Connection conn = db.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                String pattern = "%" + (keyword == null ? "" : keyword.trim()) + "%";
                ps.setString(1, pattern);
                ps.setString(2, pattern);
                if (hasRole) {
                    ps.setString(3, roleFilter);
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User user) {
        try {
            Connection conn = db.getConnection();
            if (user.getId() == 0 || findById(user.getId()) == null) {
                String sql = "INSERT INTO public.users (name, email, password, role, is_active) "
                           + "VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"})) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getRole());
                    ps.setBoolean(5, user.isActive());
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            user.setId(rs.getInt(1));
                        }
                    }
                }
            } else {
                String sql = "UPDATE public.users "
                           + "SET name = ?, email = ?, password = ?, role = ?, is_active = ? "
                           + "WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getRole());
                    ps.setBoolean(5, user.isActive());
                    ps.setInt(6, user.getId());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateRoleAndStatus(int id, String role, boolean isActive) {
        String sql = "UPDATE public.users SET role = ?, is_active = ? WHERE id = ?";
        try {
            Connection conn = db.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, role);
                ps.setBoolean(2, isActive);
                ps.setInt(3, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM public.users WHERE id = ?";
        try {
            Connection conn = db.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}