/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Cathal
 */
public interface DAO <T> {
    
      Optional<T> get(int id);
    
    List<T> getAll();
    
    List<T> getAll(Object o);
    
    int save(T t);
    
    int update(T t);
    
    int delete(T t);
    
    
    
}
