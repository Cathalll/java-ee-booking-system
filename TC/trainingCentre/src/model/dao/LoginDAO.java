/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.LoginBean;
import java.util.Optional;
import model.User;

/**
 *
 * @author Cathal
 */
public interface LoginDAO {
    
    public boolean checkUserExists(LoginBean loginBean);
    
    public User returnUserIfExists(LoginBean loginBean);
    
    public Optional<User> login(LoginBean loginBean);
    
    
}
