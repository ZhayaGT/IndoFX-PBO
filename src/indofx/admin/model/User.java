/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.model;

/**
 *
 * @author ROG
 */
public class User extends BaseEntity{
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean active;

    public User() {
    }

    public User(String name, String email, String password, String role, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @Override
    public String getEntityCode() {
        return String.format("USR-%02d", id);
    }

    public String getStatusLabel() {
        return active ? "AKTIF" : "SUSPENDED";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}