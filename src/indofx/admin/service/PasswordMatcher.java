/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indofx.admin.service;

/**
 *
 * @author ROG
 */
public interface PasswordMatcher {
    boolean matches(String rawPassword, String storedPassword);
}