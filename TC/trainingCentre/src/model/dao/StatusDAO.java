/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Optional;
import model.Status;
import model.User;

/**
 *
 * @author Cathal
 */
public interface StatusDAO extends DAO<Status> {
    
    public Optional<Status> getByUser(User user);
    
}
