/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.service;

/**
 *
 * @author ROG
 */
public class PlainTextPasswordMatcher implements PasswordMatcher {
    @Override
    public boolean matches(String rawPassword, String storedPassword) {
        return rawPassword != null && rawPassword.equals(storedPassword);
    }  
}