/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indofx.admin.repository;

/**
 *
 * @author ROG
 */

import indofx.admin.model.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    User findByEmailOrName(String emailOrName);
    List<User> search(String keyword, String roleFilter);
    boolean updateRoleAndStatus(int id, String role, boolean isActive);
}