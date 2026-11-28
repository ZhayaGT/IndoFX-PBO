/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indofx.admin.repository;

/**
 *
 * @author ROG
 */
public interface CrudRepository<T> extends ReadRepository<T> {
    void save(T entity);
    boolean delete(int id);
}