/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.config;

/**
 *
 * @author ROG
 */

import indofx.admin.repository.PostgresUserRepository;
import indofx.admin.repository.UserRepository;
import indofx.admin.service.AuthService;
import indofx.admin.service.PasswordMatcher;
import indofx.admin.service.PlainTextPasswordMatcher;

public class ServiceFactory {
    private static final DatabaseConnection DB = new PostgresConnection();

    public static UserRepository createUserRepository() {
        return new PostgresUserRepository(DB);
    }

    public static AuthService createAuthService() {
        PasswordMatcher matcher = new PlainTextPasswordMatcher();
        return new AuthService(createUserRepository(), matcher);
    }
}