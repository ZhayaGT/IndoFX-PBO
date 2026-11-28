/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indofx.admin.repository;

/**
 *
 * @author ROG
 */

import java.util.List;

public interface ReadRepository<T> {
    T findById(int id);
    List<T> findAll();
}