/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.service;

/**
 *
 * @author ROG
 */

import indofx.admin.model.User;
import indofx.admin.repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordMatcher passwordMatcher;

    public AuthService(UserRepository userRepository, PasswordMatcher passwordMatcher) {
        this.userRepository = userRepository;
        this.passwordMatcher = passwordMatcher;
    }

    public User loginAdmin(String emailOrName, String rawPassword) {
        if (emailOrName == null || emailOrName.trim().isEmpty()
                || rawPassword == null || rawPassword.isEmpty()) {
            return null;
        }

        User user = userRepository.findByEmailOrName(emailOrName);
        if (user == null) {
            return null;
        }

        boolean isAdmin = "Admin".equalsIgnoreCase(user.getRole());
        boolean isPasswordValid = passwordMatcher.matches(rawPassword, user.getPassword());

        if (user.isActive() && isAdmin && isPasswordValid) {
            return user;
        }
        return null;
    }
}